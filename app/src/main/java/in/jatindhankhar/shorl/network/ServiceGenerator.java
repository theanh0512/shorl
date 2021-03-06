package in.jatindhankhar.shorl.network;

import android.content.Context;

import java.io.IOException;

import in.jatindhankhar.shorl.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jatin on 12/18/16.
 */

public class ServiceGenerator {
    public static final String API_BASE_URL = "https://www.googleapis.com/urlshortener/v1/url/";
    private static Context mContext;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());


    public ServiceGenerator(Context mContext) {
        this.mContext = mContext;
    }

    public static <S> S createService(Class<S> serviceClass, final String accessToken) {


        if (accessToken != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    // Adding headers
                    return chain.proceed(original.newBuilder()
                            .header("Authorization", "Bearer " + accessToken.toString()).build());
                }
            });
        }

        // Enable logging for DEBUG purposes
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);

        }
        httpClient.authenticator(new TokenAuthenticator(mContext));


        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
