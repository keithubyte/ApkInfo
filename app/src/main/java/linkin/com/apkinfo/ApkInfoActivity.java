package linkin.com.apkinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.VolleyError;
import com.linkin.base.nhttp.base.GetRequest;
import com.linkin.base.nhttp.base.ReqType;
import com.linkin.base.nhttp.inter.IHttpObserver;
import com.linkin.base.nhttp.volley.VolleyHelper;
import com.linkin.base.utils.Log;

public class ApkInfoActivity extends AppCompatActivity {

    private static final String TAG = "ApkInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_info);

        VolleyHelper.init(this);

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testThirdPartyGet();
            }
        });
    }

    public void testThirdPartyGet() {
        new GetRequest() { // 每个请求都对应一个任务 id
            @Override
            public ReqType reqType() {
                // 指定为明文类型
                return ReqType.PLAIN;
            }

            @Override
            public String apiUrl() {
                // 指定接口地址（第三方接口）
                return "http://news-at.zhihu.com/api/4/version/android/2.3.0";
            }
        }.execute( // 执行请求
                new IHttpObserver() { // 指定观察者
                    @Override
                    public void onHttpSuccess(String taskId, Object responseBody) {
                        ZhiHuUpdateResp resp = (ZhiHuUpdateResp) responseBody;
                        Log.e(TAG, "resp = " + resp.toString());
                    }

                    @Override
                    public void onHttpError(String taskId, int statusCode, Object error) {
                        Log.e(TAG, "error = " + ((VolleyError) error).getLocalizedMessage());
                    }
                },
                ZhiHuUpdateResp.class // 指定返回数据要转化的类型
        );
    }
}
