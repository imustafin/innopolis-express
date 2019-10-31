package com.mfq.foodle.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration;
import com.mfq.foodle.BuildConfig;
import com.mfq.foodle.R;
import com.mfq.foodle.adapters.FoodDetailsAdapter;
import com.mfq.foodle.backdrop.BackdropManger;
import com.mfq.foodle.events.SelectedProductEvent;
import com.mfq.foodle.helper.InputMethodHelper;
import com.mfq.foodle.models.product.Product;
import com.mfq.foodle.moofi.adapters.AssistantChatAdapter;
import com.mfq.foodle.staggeredgridlayout.ProductGridItemDecoration;
import com.mfq.foodle.staggeredgridlayout.StaggeredFoodAdapter;
import com.mfq.foodle.utils.GsonHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FoodleAssistant extends Activity implements View.OnClickListener, TextToSpeech.OnInitListener {
    private static final String TAG = FoodleAssistant.class.getSimpleName();
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_RUDE = "rude";
    private static final String KEY_PREFERENCE_DONE = "PREFERENCE_DONE";
    private static final String KEY_PHONE_DONE = "PHONE_DONE";
    private static final String KEY_CART = "CART";
    private static final int RESULT_SPEECH = 0;
    private static final int MY_DATA_CHECK_CODE = 1;
    private static final int ASSISTANT_LV1 = 1;
    private static final int ASSISTANT_LV2 = 2;
    private static final int ASSISTANT_LV3 = 3;
    private static final int ASSISTANT_LV4 = 4;
    private int mAssistantType = ASSISTANT_LV1;
    private TextToSpeech mTts;
    private StaggeredFoodAdapter mItemsAdapter;
    private FloatingActionButton mFab;
    private BackdropManger mBackdropManger;
    private RecyclerView mChatRecyclerView;
    private AssistantChatAdapter mChatAdapter;
    private RecyclerView mItemRecycler;
    private Product mProduct;
    private Integer mQty;
    private EditText mEditTextContainer;
    BottomAppBar mBottomAppBar;
    private UtteranceProgressListener mUtteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "onStart() called with: utteranceId = [" + utteranceId + "]");


        }

        @Override
        public void onDone(String utteranceId) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "onDone() called with: utteranceId = [" + utteranceId + "]");

            switch (utteranceId) {
                case KEY_QUANTITY:
                    runOnUiThread(() -> buildMelaPreferenceAdapter());
                    break;
                case KEY_RUDE:
                    finish();
                    break;
                case KEY_PHONE_DONE:
                    runOnUiThread(() -> buildPhoneNumberLogic());
                    break;
                case KEY_CART:
                    startActivity(new Intent(FoodleAssistant.this, CartActivity.class));
                    finish();
                    break;
            }
        }

        @Override
        public void onError(String utteranceId) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "onError() called with: utteranceId = [" + utteranceId + "]");

        }
    };

    private void buildPhoneNumberLogic() {
        mBottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        mAssistantType = ASSISTANT_LV4;

        new Handler().postDelayed(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(mEditTextContainer, mEditTextContainer.getWidth() / 2,
                        mEditTextContainer.getHeight() / 2, 0,
                        (float) Math.hypot(mEditTextContainer.getWidth(), mEditTextContainer.getHeight()));
                mEditTextContainer.setVisibility(View.VISIBLE);
                circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
                circularReveal.setDuration(500);
                circularReveal.start();
                mFab.setTag(KEY_PHONE_DONE);

            }
        }, 350);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodle_assistant);

        initItemRecycler();
        initChatRecycler();
        initSpeechRecognizer();
        initFoodApi("json_data");
        initBackdrop();

        mFab = findViewById(R.id.fab);
        mBottomAppBar = findViewById(R.id.bottomBar);
        mEditTextContainer = findViewById(R.id.number_edit_text);
        mFab.setOnClickListener(this);
    }

    private void initBackdrop() {
        mBackdropManger = new BackdropManger(
                this,
                findViewById(R.id.backdrop_sheet),
                new AccelerateDecelerateInterpolator(),
                null, // Menu open icon
                null);
    }

    private void initSpeechRecognizer() {
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
    }

    private void initChatRecycler() {
        mChatRecyclerView = findViewById(R.id.assistantRecycler);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChatRecyclerView.setHasFixedSize(true);
        mChatRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mChatAdapter = new AssistantChatAdapter();
        mChatRecyclerView.setAdapter(mChatAdapter);


    }

    public List<Product> loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            return GsonHelper.parseGsonArray(json, Product[].class);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void initFoodApi(String fileName) {
        Observable.just(loadJSONFromAsset(fileName))
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> products) {
                        initItemRecycler();
                        mItemsAdapter.setProductList(products);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initItemRecycler() {
        mItemRecycler = findViewById(R.id.items_recycler);
        mItemRecycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });
        mItemRecycler.setLayoutManager(gridLayoutManager);
        mItemsAdapter = new StaggeredFoodAdapter();
        mItemRecycler.setAdapter(mItemsAdapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        if (mItemRecycler.getItemDecorationCount() == 0)
            mItemRecycler.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));



    }

    private void startRecognizeSpeech() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ar");

        try {
            startActivityForResult(intent, RESULT_SPEECH);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(
                    getApplicationContext(),
                    "Oops! First you must download \"Voice Search\" App from Store",
                    Toast.LENGTH_SHORT).show();
        }


    }


    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                mTts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        } else if (requestCode == RESULT_SPEECH) {
            if (resultCode == RESULT_OK && null != data) {
                // userText is received form google
                ArrayList<String> suggestionUtternces = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //you can find your result in userText Arraylist

//                for (String suggestionUtternce : suggestionUtternces) {
//                    mChatAdapter.addUserChat(suggestionUtternce);
//                }


                String userText = suggestionUtternces.get(0);
                userText = userText.replaceAll("movie", "Moofi");
                if (mAssistantType != ASSISTANT_LV2)
                    mChatAdapter.addUserChat(userText);
                mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());

                String assistantText;
                if (userText.contains("hi") || userText.contains("hello")) {
                    assistantText = "Hello nice to meet you !";
                    speak(assistantText);
                    return;
                } else if (userText.contains("thank you")) {
                    assistantText = "you are welcome";
                    speak(assistantText);
                    return;
                } else if (userText.contains("I love you")) {
                    assistantText = "Wow!, I love you too";
                    speak(assistantText);
                    return;
                } else if (userText.contains("I hate you")) {
                    assistantText = "Ok, That is rude, I am Shutting down";
                    speak(assistantText, KEY_RUDE);
                    return;
                }

                switch (mAssistantType) {
                    case ASSISTANT_LV1:
                        assistantLv1(userText);
                        break;
                    case ASSISTANT_LV2:
                        assistantLv2(suggestionUtternces);
                        break;
                    case ASSISTANT_LV3:
                        assistantLv3(userText);
                        break;
                }


            }
        }
    }

    private void assistantLv3(String text) {
        String assistantText = "";
        if (text.contains("yes")) {
            assistantText = "Ok, here is a list of a Dessert Recommendation";
            speak(assistantText);
            mBackdropManger.showHideBackDrop();
        } else if (text.contains("no")) {
            moveToLv4();
        } else {
            assistantText = " I'm sorry i did not understand I'm still young and learning , do you want any dessert ?";
            speak(assistantText);
        }

    }

    private void moveToLv4() {
        String assistantText;
        assistantText = "Ok, now i need you phone number, you will receive a Verification code , don't worry i will verified it for you !";
//        assistantText = "Ok,phone";
        speak(assistantText, KEY_PHONE_DONE);
        mFab.setImageResource(R.drawable.ic_round_done_24px);
        mFab.setTag(KEY_PHONE_DONE);
        mAssistantType = ASSISTANT_LV4;
    }

    private void assistantLv2(ArrayList<String> suggestionUtterances) {
        String regex = "\\d+";
        for (String text : suggestionUtterances) {
            if (text.matches(regex)) {
                mChatAdapter.addUserChat(text);
                mQty = Integer.valueOf(text);
                speak("ok , i will prepare a  " + text + " Meals to you", KEY_QUANTITY);
                return;
            }
        }
        mChatAdapter.addUserChat(suggestionUtterances.get(0));
        speak("Im sorry please say a number ");


    }

    private void buildMelaPreferenceAdapter() {
        mItemRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FoodDetailsAdapter adapter = new FoodDetailsAdapter();
        mItemRecycler.setAdapter(adapter);
        adapter.setProduct(mProduct);
        adapter.addItem(mQty);
        speak("Please choose The Toppings and extras you want");
        mBackdropManger.showHideBackDrop();
        mFab.setImageResource(R.drawable.ic_round_done_24px);
        mFab.setTag(KEY_PREFERENCE_DONE);
        mItemRecycler.addItemDecoration(new EndOffsetItemDecoration(200));


    }

    private void assistantLv1(String text) {
        String assistantText = "";
        if (text.contains("recommendation") || text.contains("yes")) {
            assistantText = "Ok, here is a list of Recommendation";
            speak(assistantText);
            mBackdropManger.showHideBackDrop();
        } else {
            assistantText = " I'm sorry i did not understand I'm still young and learning , do you want me to show you my recommendations ?";
            speak(assistantText);
        }
    }

    private void speak(String assistantText) {
        mTts.speak(assistantText, TextToSpeech.QUEUE_ADD, null);
        mChatAdapter.addAssistantChat(assistantText);
        mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());
    }

    private void speak(String assistantText, String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, id);
        mTts.speak(assistantText, TextToSpeech.QUEUE_ADD, hashMap);
        mChatAdapter.addAssistantChat(assistantText);
        mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());
    }


    @Override
    public void onClick(View view) {

        mTts.stop();
        switch (view.getTag() != null ? view.getTag().toString() : "") {
            case KEY_PREFERENCE_DONE:
                mBackdropManger.showHideBackDrop();
                speak("Ok, Do you want some dessert with the meals?");
                mFab.setImageResource(R.drawable.ic_round_mic_24px);
                mAssistantType = ASSISTANT_LV3;
                mFab.setTag(null);
                new Handler().postDelayed(() -> initFoodApi("dessert_json_data"), 500);
                break;
            case KEY_PHONE_DONE:
                String text = "Please Fill yor phone number";
                if (mEditTextContainer.getText().toString().isEmpty()) {
                    speak(text);
                } else {
                    InputMethodHelper.hideKeyboardFrom(this, view);
                    mFab.setTag(null);
                    mFab.hide();
                    mEditTextContainer.setVisibility(View.INVISIBLE);
                    mFab.setImageResource(R.drawable.ic_round_mic_24px);
                    mChatAdapter.addUserChat(mEditTextContainer.getText().toString());
                    text = "ok got it, i will verified it now ...";
                    speak(text);
                    String text2 = "Verified!";
                    new Handler().postDelayed(() -> {
                        speak(text2);
                        speak("i will set delivery location to here wait a second");
                        new Handler().postDelayed(() -> {
                            speak("Done!, i will send you to the cart to complete your order ");
                            speak(" Bye bye !", KEY_CART);
                        }, 7000);
                    }, 4000);

                }
                break;
            default:
                if (mBackdropManger.isBackdropShown())
                    mBackdropManger.showHideBackDrop();
                startRecognizeSpeech();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        mTts.stop();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        mTts.setSpeechRate(0.7f);
        mTts.setPitch(1.5f);
        mTts.setLanguage(Locale.US);
        mTts.setOnUtteranceProgressListener(mUtteranceProgressListener);
        String myText2 = "Hi , I'm Moofi,\nI am your assistant , here to help you order , try saying show me recommendation";
        mTts.speak(myText2, TextToSpeech.QUEUE_FLUSH, null);
        mChatAdapter.addAssistantChat(myText2);

    }

    @Override
    public void onBackPressed() {
        if (mBackdropManger.isBackdropShown())
            mBackdropManger.showHideBackDrop();
        else
            super.onBackPressed();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedProductEvent(SelectedProductEvent event) {
        mProduct = event.mProduct;
        mBackdropManger.showHideBackDrop();
        if (mProduct.getType().equals(Product.TYPE_FOOD)) {
            mAssistantType = ASSISTANT_LV2;
            String myText2 = "Ok you selected " + mProduct.getName() + ", what a yummy choice, how many quantity do you want ? ";
            speak(myText2);
        } else {
            String myText2 = "Yummy our " + mProduct.getName() + ", is very delicious! ";
            speak(myText2);
            moveToLv4();

        }
    }


}
