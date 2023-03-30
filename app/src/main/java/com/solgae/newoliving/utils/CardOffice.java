package com.solgae.newoliving.utils;

public class CardOffice {

// 발급사 이름
    public static String ISSUER(String code) {

        String issuer_name = "";

        switch(code) {
            case "002":
                issuer_name = "광주은행";
                break;
            case "006":
                issuer_name = "하나카드";
                break;
            case "008":
                issuer_name = "하나구외환";
                break;
            case "010":
                issuer_name = "전북은행";
                break;
            case "011":
                issuer_name = "제주은행";
                break;
            case "016":
                issuer_name = "국민카드사";
                break;
            case "017":
                issuer_name = "수협";
                break;
            case "018":
                issuer_name = "NH카드";
                break;
            case "019":
                issuer_name = "한미은행";
                break;
            case "022":
                issuer_name = "씨티카드사";
                break;
            case "026":
                issuer_name = "비씨카드사";
                break;
            case "027":
                issuer_name = "현대카드사";
                break;
            case "029":
                issuer_name = "신한카드사";
                break;
            case "031":
                issuer_name = "삼성카드사";
                break;
            case "034":
                issuer_name = "신세계백화점(자사)";
                break;
            case "045":
                issuer_name = "신롯데카드사";
                break;
            case "047":
                issuer_name = "롯데(동양)카드사";
                break;
        }
        return issuer_name;
    }

// 매입사 이름
    public String ACQUIRER(String code) {
        String acquirer_name = "";
        switch(code) {
            case "002":
                acquirer_name = "비씨카드사";
                break;
            case "006":
                acquirer_name = "하나카드";
                break;
            case "008":
                acquirer_name = "하나구외환";
                break;
            case "010":
                acquirer_name = "비씨카드사";
                break;
            case "011":
                acquirer_name = "비씨카드사";
                break;
            case "016":
                acquirer_name = "국민카드사";
                break;
            case "017":
                acquirer_name = "비씨카드사";
                break;
            case "018":
                acquirer_name = "NH카드";
                break;
            case "019":
                acquirer_name = "비씨카드사";
                break;
            case "022":
                acquirer_name = "비씨카드사";
                break;
            case "026":
                acquirer_name = "비씨카드사";
                break;
            case "027":
                acquirer_name = "현대카드사";
                break;
            case "029":
                acquirer_name = "신한카드사";
                break;
            case "031":
                acquirer_name = "삼성카드사";
                break;
            case "034":
                acquirer_name = "비씨카드사";
                break;
            case "045":
                acquirer_name = "신롯데카드사";
                break;
            case "047":
                acquirer_name = "신롯데카드사";
                break;
        }
        return acquirer_name;
    }
}
