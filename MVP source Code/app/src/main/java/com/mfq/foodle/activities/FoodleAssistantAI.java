package com.mfq.foodle.activities;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.NonNull;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mfq.foodle.BuildConfig;
import com.mfq.foodle.R;
import com.mfq.foodle.ai.AiConfig;
import com.mfq.foodle.coustemviews.DynamicChip;
import com.mfq.foodle.events.SelectedAddressEvent;
import com.mfq.foodle.events.SelectedProductEvent;
import com.mfq.foodle.helper.InputMethodHelper;
import com.mfq.foodle.models.cart.Cart;
import com.mfq.foodle.models.product.Product;
import com.mfq.foodle.moofi.adapters.AssistantChatAdapter;
import com.mfq.foodle.moofi.models.Moofi;
import com.mfq.foodle.moofi.models.MoofiPayload;
import com.mfq.foodle.moofi.models.MoofiUi;
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
import java.util.Map;

import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.GsonFactory;
import ai.api.model.AIError;
import ai.api.model.AIEvent;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.ResponseMessage;
import ai.api.model.Result;
import ai.api.ui.AIButton;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FoodleAssistantAI extends Activity implements AIButton.AIButtonListener, TextToSpeech.OnInitListener, View.OnClickListener {

    private static final String KEY_GIPHY_API = "fy8GrkvSn1Sy7YNoye5Hp4pyEDINh2R0";
    private static final int MY_DATA_CHECK_CODE = 1;
    private static final int REQUEST_AUDIO_PERMISSIONS_ID = 33;
    private static final String TAG = FoodleAssistantAI.class.getSimpleName();


    private UtteranceProgressListener mUtteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "onStart() called with: utteranceId = [" + utteranceId + "]");


        }

        @Override
        public void onDone(String utteranceId) {
            startActivity(new Intent(FoodleAssistantAI.this, CartActivity.class));
            finish();
        }

        @Override
        public void onError(String utteranceId) {

        }
    };

    private Gson mGson = GsonFactory.getGson();
    private TextToSpeech mTts;
    private AssistantChatAdapter mChatAdapter;
    private AIDataService mAIDataService;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private List<Product> mDessertProducts;
    private List<Product> mMealsProducts;
    private List<Product> mCartProducts = new ArrayList<>();
    private RecyclerView mChatRecyclerView;
    private EditText mQueryEditText;
    private ImageView mSendText;
    private GPHApiClient mGPHApiClient;
    private View mPermissionText;
    private View mTypingContainer;
    private LottieAnimationView mTypingAnimation;
    private ChipGroup mChipGroup;
    private MediaPlayer mPlayer;
    private Product mOrderdProduct;
    private long mFinishCounter;


    @Override
    protected void onStart() {
        super.onStart();
        checkAudioRecordPermission();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moofi);
        mQueryEditText = findViewById(R.id.query_edit_text);
        mSendText = findViewById(R.id.fab);
        mTypingContainer = findViewById(R.id.moofi_typing_container);
        mTypingAnimation = findViewById(R.id.moofi_typingAnimation);
        mChipGroup = findViewById(R.id.moofi_chip_group);
        mPermissionText = findViewById(R.id.permission);
        mPermissionText.setOnClickListener(v -> requestPermissionDialog());
    }

    private void init() {
        initAi();
        initSpeechRecognizer();
        initChatRecycler();
        mSendText.setOnClickListener(this);
        findViewById(R.id.close).setOnClickListener(v -> onBackPressed());
        startMoofi();
        requestDessertApi();
        requestMealsApi();
        mGPHApiClient = new GPHApiClient(KEY_GIPHY_API);
    }


    private void initAi() {
        final AIConfiguration config = new AIConfiguration(AiConfig.ACCESS_TOKEN,
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        config.setRecognizerStartSound(getResources().openRawResourceFd(R.raw.test_start));
        config.setRecognizerStopSound(getResources().openRawResourceFd(R.raw.test_stop));
        config.setRecognizerCancelSound(getResources().openRawResourceFd(R.raw.test_cancel));
        mPlayer = MediaPlayer.create(this, R.raw.typing_sound);
        mAIDataService = new AIDataService(this, config);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // use this method to disconnect from speech recognition service
        // Not destroying the SpeechRecognition object in onPause method would block other apps from using SpeechRecognition service
        if (mTts != null) mTts.stop();
        if (mPlayer != null) {
            mPlayer.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initChatRecycler() {
        mChatRecyclerView = findViewById(R.id.assistantRecycler);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChatRecyclerView.setHasFixedSize(true);
        mChatRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mChatAdapter = new AssistantChatAdapter();
        mChatRecyclerView.setAdapter(mChatAdapter);


    }

    private void requestMealsApi() {
        Observable.just(loadJSONFromAsset("json_data"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        mMealsProducts = products;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestDessertApi() {
        Observable.just(loadJSONFromAsset("dessert_json_data"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        mDessertProducts = products;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onResult(final AIResponse response) {
        runOnUiThread(() -> {
            mQueryEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            final Result result = response.getResult();

            String speech = result.getFulfillment().getSpeech();
            if (speech.isEmpty()) {
                if (mFinishCounter >= 2) {
                    new Handler().postDelayed(() -> finish(), 3000);
                    String assistantText = "ok it seems there something wrong , bye for now ";
                    speak(assistantText);
                    mChatAdapter.addAssistantChat(assistantText);
                    return;
                }
                mFinishCounter++;
                String assistantText = "Sorry i cant find anything about " + result.getResolvedQuery();
                speak(assistantText);
                mChatAdapter.addAssistantChat(assistantText);
                return;
            }
            mFinishCounter = 0;
            speak(speech);
            handleMoofiPayload(result);


            handleActions(result);
            checkForGifs(result);

            logDialogFlow(result, response);
        });
    }

    private void handleMoofiPayload(Result result) {
        List<ResponseMessage> messages = result.getFulfillment().getMessages();
        if (messages == null || messages.size() < 2) {
            mChatAdapter.addAssistantChat(result.getFulfillment().getSpeech());
            return;
        }
        for (ResponseMessage responseMessage : messages) {
            if (responseMessage instanceof ResponseMessage.ResponsePayload) {
                ResponseMessage.ResponsePayload payload = (ResponseMessage.ResponsePayload) responseMessage;
                String json = payload.getPayload().toString();

                MoofiPayload moofi = mGson.fromJson(json, MoofiPayload.class);

                if (moofi == null || moofi.getMoofiUi() == null)
                    return;
                handleChipSuggestions(moofi.getMoofiUi().getSuggestionChips());
                handleDisplayPrompt(result, moofi);
                handleItemView(moofi.getMoofiUi().getRecyclerItemView());


                if (BuildConfig.DEBUG)
                    Log.d(TAG, "handleMoofiPayload: " + json);

            }

        }
    }

    private void handleItemView(String itemView) {
        switch (itemView) {
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_MEALS:
                mChatAdapter.addProductList(mMealsProducts);
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_DESSERT:
                mChatAdapter.addProductList(mDessertProducts);
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_MOOFI:
                mChatAdapter.addMoofiCanDo();
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_DEFAULT_ADDRESS:
                mChatAdapter.addDefaultAddress();
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_ADDRESS:
                mChatAdapter.addListAddress();
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_FOODLE_DRIVE:
                mChatAdapter.addFoodleDrive();
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_CART_ITEM:
                mChatAdapter.addCartItems(mCartProducts);
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_CART_VIEW:
                mChatAdapter.addCartView(mCartProducts, "", "", "");
                break;
            case MoofiUi.MoofiRecyclerItemType.ITEM_VIEW_INVOICE:
                mChatAdapter.addInvoiceView(mCartProducts);
                break;


        }
        mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());

    }

    private void handleDisplayPrompt(Result result, MoofiPayload moofi) {
        String displayPrompt = moofi.getMoofi().getDisplayPrompt();
        if (!displayPrompt.isEmpty())
            mChatAdapter.addAssistantChat(displayPrompt);
        else
            mChatAdapter.addAssistantChat(result.getFulfillment().getSpeech());
    }

    private void handleChipSuggestions(List<String> suggestionChips) {
        if (suggestionChips.isEmpty())
            return;

        mChipGroup.setVisibility(View.VISIBLE);
        for (String suggestionChip : suggestionChips) {
            DynamicChip chip =
                    (DynamicChip) LayoutInflater.from(this).inflate(R.layout.chip_item_action, mChipGroup, false);
            chip.setText(suggestionChip);
            chip.setOnClickListener(view -> sendRequestToMoofi(((DynamicChip) view).getText().toString()));
            mChipGroup.addView(chip);
        }

    }

    private void logDialogFlow(Result result, AIResponse response) {
        Log.d(TAG, "onResult" + mGson.toJson(response));
        Log.i(TAG, "Received success response");
        Log.i(TAG, "Status code: " + response.getStatus().getCode());
        Log.i(TAG, "Status type: " + response.getStatus().getErrorType());
        Log.i(TAG, "Resolved query: " + result.getResolvedQuery());
        Log.i(TAG, "Action: " + result.getAction());
        Log.i(TAG, "Speech: " + result.getFulfillment().getSpeech());
        if (result.getMetadata() != null) {
            Log.i(TAG, "Intent id: " + result.getMetadata().getIntentId());
            Log.i(TAG, "Intent name: " + result.getMetadata().getIntentName());
        }

        if (result.getParameters() != null && !result.getParameters().isEmpty()) {
            Log.i(TAG, "Parameters: ");
            for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
            }
        }
    }

    private void checkForGifs(Result result) {
        if (!result.getAction().equals(Moofi.Actions.ACTION_SEND_GIF)) {
            return;
        }
        mChatAdapter.addAssistantChat(result.getFulfillment().getSpeech());
        List<ResponseMessage> messages = result.getFulfillment().getMessages();
        Observable.fromArray(messages)
                .flatMapIterable(messageList -> messages)
                .filter(responseMessage -> responseMessage instanceof ResponseMessage.ResponsePayload)
                .subscribe(new Observer<ResponseMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseMessage responseMessage) {
                        ResponseMessage.ResponsePayload responsePayload = (ResponseMessage.ResponsePayload) responseMessage;
                        String gif_query = responsePayload.getPayload().getAsJsonObject().get("gif_query").getAsString();
                        createGif(gif_query);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void handleActions(Result resultAction) {
        String action = resultAction.getAction();
        if (action.isEmpty())
            return;


        switch (action) {
            case Moofi.Actions.ACTION_ORDER_PRODUCT:
                if (mOrderdProduct != null)
                    mChatAdapter.addProduct(mOrderdProduct);
                else {
                    List<Product> products = new ArrayList<>();
                    products.addAll(mDessertProducts);
                    products.addAll(mMealsProducts);
                    for (Product product : products) {
                        if (resultAction.getResolvedQuery().contains(product.getName().toLowerCase())) {
                            mOrderdProduct = product;
                            mChatAdapter.addProduct(mOrderdProduct);
                        }
                    }

                }

                break;

            case Moofi.Actions.ACTION_ADD_TO_CART:
                mCartProducts.add(mOrderdProduct);
                mOrderdProduct = null;
                break;
            case Moofi.Actions.ACTION_EXIT:
                new Handler().postDelayed(() -> finish(), 3000);
                break;

            case Moofi.Actions.ACTION_ENTER_PHONE:
                mQueryEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                mQueryEditText.setText("07");
                mQueryEditText.requestFocus();
                mQueryEditText.invalidate();
                InputMethodHelper.showKeyboardFrom(FoodleAssistantAI.this);
                break;
        }

    }

    private void goToCart() {
        String assistantText = "ok now i will leave you to your cart to confirm your order, Bye bye !";
        speak(assistantText, "id");
        mChatAdapter.addAssistantChat(assistantText);
    }


    private void addToCart(Result resultAction) {
        HashMap<String, JsonElement> params = resultAction.getParameters();
        List<Product> products = new ArrayList<>();
        products.addAll(mMealsProducts);
        products.addAll(mDessertProducts);

        //  number: [3,2]
        //  entity-meals: ["zinger","shawerma"]
        for (Map.Entry<String, JsonElement> entry : params.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            if (key.contains(Moofi.Entity.ENTITY_MEALS) || key.contains(Moofi.Entity.ENTITY_DESSERT)) {
                for (JsonElement jsonElement : value.getAsJsonArray()) {
                    for (Product product : products) {
                        String asString = jsonElement.getAsString().toLowerCase();
                        String name = product.getName().toLowerCase();
                        if (name.contains(asString)) {
                            Cart.getInstance().addProduct(product);
                        }
                    }

                }

            }


            if (BuildConfig.DEBUG) {
                List<Product> cartP = Cart.getInstance().getProducts();
                Log.d(TAG, "handleActions: " + cartP);
            }
        }
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
            List<Product> products = GsonHelper.parseGsonArray(json, Product[].class);
            return products;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public void onError(final AIError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onError");
//                resultTextView.setText(error.toString());
            }
        });
    }

    @Override
    public void onCancelled() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCancelled");
//                resultTextView.setText("");
            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void speak(String assistantText) {
        mTts.speak(assistantText, TextToSpeech.QUEUE_ADD, null);
        mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());
    }

    private void speak(String assistantText, String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, id);
        mTts.speak(assistantText, TextToSpeech.QUEUE_ADD, hashMap);
        mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());
    }

    private void initSpeechRecognizer() {
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
    }

    @Override
    public void onInit(int status) {
        mTts.setSpeechRate(0.9f);
        mTts.setPitch(1.5f);
        mTts.setLanguage(Locale.US);
        mTts.setOnUtteranceProgressListener(mUtteranceProgressListener);

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
        }
    }


    protected void checkAudioRecordPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                mPermissionText.setVisibility(View.VISIBLE);

            } else {

                // No explanation needed, we can request the permission.

                requestPermissionDialog();

            }
        } else {
            init();
        }
    }

    private void requestPermissionDialog() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                REQUEST_AUDIO_PERMISSIONS_ID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSIONS_ID: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mPermissionText.setVisibility(View.GONE);
                    init();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    checkAudioRecordPermission();
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        String query = mQueryEditText.getText().toString();
        if (query.isEmpty())
            return;
        sendRequestToMoofi(query);
    }

    private void HandleUiOnRequestSend() {
        mTts.stop();
        mChipGroup.removeAllViews();
        mChipGroup.setVisibility(View.GONE);
        mTypingContainer.setVisibility(View.VISIBLE);
        mTypingAnimation.playAnimation();
        mQueryEditText.setEnabled(false);
        mQueryEditText.setText("");
//        mPlayer.start();
    }

    private void sendRequestToMoofi(String query) {
        HandleUiOnRequestSend();
        mChatAdapter.addUserChat(query);
        sendAIRequest(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AIResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);

                    }

                    @Override
                    public void onNext(AIResponse aiResponse) {
                        finishRequest();
                        onResult(aiResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        new AIError(e.getMessage());
                        finishRequest();
                    }

                    @Override
                    public void onComplete() {
                        finishRequest();
                    }
                });


    }

    private void startMoofi() {

        startRequest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AIResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);

                    }

                    @Override
                    public void onNext(AIResponse aiResponse) {
                        finishRequest();
                        onResult(aiResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        new AIError(e.getMessage());
                        finishRequest();
                    }

                    @Override
                    public void onComplete() {
                        finishRequest();
                    }
                });


    }

    private void finishRequest() {
        mTypingContainer.setVisibility(View.GONE);
        mTypingAnimation.pauseAnimation();
        mQueryEditText.setEnabled(true);
    }

    private Observable<AIResponse> sendAIRequest(String query) {

        Observable<AIResponse> AIObservable = Observable.create(emitter -> {

            final AIRequest request = new AIRequest();
            request.setQuery(query);
            try {
                emitter.onNext(mAIDataService.request(request));
            } catch (AIServiceException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            emitter.onComplete();
        });

        return AIObservable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private Observable<AIResponse> startRequest() {

        Observable<AIResponse> AIObservable = Observable.create(emitter -> {

            final AIRequest request = new AIRequest();
            request.setResetContexts(true);
            AIEvent aiEvent = new AIEvent();
            aiEvent.setName(Moofi.Events.EVENT_WELCOME);
            request.setEvent(aiEvent);
            try {
                emitter.onNext(mAIDataService.request(request));
            } catch (AIServiceException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            emitter.onComplete();
        });

        return AIObservable;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedProductEvent(SelectedAddressEvent event) {


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedProductEvent(SelectedProductEvent event) {
        sendRequestToMoofi("order " + event.mProduct.getName());
        mOrderdProduct = event.mProduct;
    }

    public void createGif(String query) {
        /// Random Gif
        mGPHApiClient.random(query, MediaType.gif, null, (result, e) -> {
            if (result == null) {
                // Do what you want to do with the error
            } else {
                if (result.getData() != null) {
                    mChatAdapter.addGif(result.getData().getImages().getFixedWidthDownsampled().getGifUrl());
                    mChatRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());

                } else {
                    Log.e("giphy error", "No results found");
                }
            }
        });
    }


}
