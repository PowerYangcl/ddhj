package cn.com.ddhj.model.trade;

import cn.com.ddhj.model.BaseModel;

public class TTradeCity extends BaseModel  {

    private String cityId;

    private String cityName;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}