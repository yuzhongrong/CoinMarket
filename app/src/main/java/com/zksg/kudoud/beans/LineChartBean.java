package com.zksg.kudoud.beans;

import android.view.FocusFinder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LineChartBean {

    @SerializedName("ERRORNO")
    private Integer eRRORNO;
    @SerializedName("GRID0")
    private GRID0DTO gRID0;

    public Integer getERRORNO() {
        return eRRORNO;
    }

    public void setERRORNO(Integer eRRORNO) {
        this.eRRORNO = eRRORNO;
    }

    public GRID0DTO getGRID0() {
        return gRID0;
    }

    public void setGRID0(GRID0DTO gRID0) {
        this.gRID0 = gRID0;
    }

    public static class GRID0DTO {
        private Integer code;
        private String message;
        private ResultDTO result;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ResultDTO getResult() {
            return result;
        }

        public void setResult(ResultDTO result) {
            this.result = result;
        }

        public static class ResultDTO {
            private List<CompositeIndexGEMDTO> compositeIndexGEM;
            private List<ClientAccumulativeRateDTO> clientAccumulativeRate;
            private List<CompositeIndexShanghaiDTO> compositeIndexShanghai;
            private List<CompositeIndexShenzhenDTO> compositeIndexShenzhen;

            public List<CompositeIndexGEMDTO> getCompositeIndexGEM() {
                return compositeIndexGEM;
            }

            public void setCompositeIndexGEM(List<CompositeIndexGEMDTO> compositeIndexGEM) {
                this.compositeIndexGEM = compositeIndexGEM;
            }

            public List<ClientAccumulativeRateDTO> getClientAccumulativeRate() {
                return clientAccumulativeRate;
            }

            public void setClientAccumulativeRate(List<ClientAccumulativeRateDTO> clientAccumulativeRate) {
                this.clientAccumulativeRate = clientAccumulativeRate;
            }

            public List<CompositeIndexShanghaiDTO> getCompositeIndexShanghai() {
                return compositeIndexShanghai;
            }

            public void setCompositeIndexShanghai(List<CompositeIndexShanghaiDTO> compositeIndexShanghai) {
                this.compositeIndexShanghai = compositeIndexShanghai;
            }

            public List<CompositeIndexShenzhenDTO> getCompositeIndexShenzhen() {
                return compositeIndexShenzhen;
            }

            public void setCompositeIndexShenzhen(List<CompositeIndexShenzhenDTO> compositeIndexShenzhen) {
                this.compositeIndexShenzhen = compositeIndexShenzhen;
            }

            public static class CompositeIndexGEMDTO {
                private String rate;
                private String tradeDate;

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getTradeDate() {
                    return tradeDate;
                }

                public void setTradeDate(String tradeDate) {
                    this.tradeDate = tradeDate;
                }
            }

            public static class ClientAccumulativeRateDTO {
                private String tradeDate;
                private Float value; //double->float

                public String getTradeDate() {
                    return tradeDate;
                }

                public void setTradeDate(String tradeDate) {
                    this.tradeDate = tradeDate;
                }

                public Float getValue() {
                    return value;
                }

                public void setValue(Float value) {
                    this.value = value;
                }
            }

            public static class CompositeIndexShanghaiDTO {
                private String rate;
                private String tradeDate;

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getTradeDate() {
                    return tradeDate;
                }

                public void setTradeDate(String tradeDate) {
                    this.tradeDate = tradeDate;
                }
            }

            public static class CompositeIndexShenzhenDTO {
                private String rate;
                private String tradeDate;
                private String end;
                private String start;

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getTradeDate() {
                    return tradeDate;
                }

                public void setTradeDate(String tradeDate) {
                    this.tradeDate = tradeDate;
                }

                public void setEnd(String end){
                    this.end=end;
                }
                public String getEnd(){
                    return end;
                }

                public void setStart(String end){
                    this.start=end;
                }
                public String getStart(){
                    return start;
                }
            }
        }
    }
}
