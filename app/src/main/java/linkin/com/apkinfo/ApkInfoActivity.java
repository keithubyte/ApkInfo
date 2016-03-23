package linkin.com.apkinfo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.linkin.base.nhttp.base.GetRequest;
import com.linkin.base.nhttp.base.ReqType;
import com.linkin.base.nhttp.inter.IHttpObserver;
import com.linkin.base.utils.Log;

public class ApkInfoActivity extends AppCompatActivity {

    private static final String TAG = "ApkInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_info);
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

    private Bitmap blur(Bitmap sentBitmap, float radius) {
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        final RenderScript rs = RenderScript.create(this);
        final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius /* e.g. 3.f */);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
        return bitmap;
    }
}
