package linkin.com.apkinfo;

import com.linkin.base.nhttp.base.GetRequest;
import com.linkin.base.nhttp.base.ReqType;

/**
 * @author yanxuhui
 * @since 2016/2/17
 */
public class ZhiHuUpdateReq extends GetRequest {

    @Override
    public ReqType reqType() {
        return ReqType.PLAIN;
    }

    @Override
    public String apiUrl() {
        return "http://news-at.zhihu.com/api/4/version/android/2.3.0";
    }
}
