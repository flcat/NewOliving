package com.solgae.newoliving.utils;

import android.widget.ImageView;

import com.solgae.newoliving.R;

import java.nio.ByteBuffer;

public class ks_dot_net {

    ByteBuffer byteBuffer = java.nio.ByteBuffer.allocateDirect(4096);
    // 포인트조회
//    0456PC050200NDPT0Q37740                S                                        9################################                                        2582700002219225                     02000000001000000000000000000000000000000000000000000000000000BB1111                                                                           RED                                                                                                                       N]
//    0452PC050200NDPT0Q37742                S                                        9################################                                        2582100002219426                     02000000001500000000000000000000000136000000001364000000000000BB1111                                                                           RED                                                                                                                       N
//    004562
    public ByteBuffer PointSearch(String cardNumber, String pasword){

        byteBuffer = java.nio.ByteBuffer.allocateDirect(4096);
        byteBuffer.put((byte)0x02);                                             // STX
        byteBuffer.put("PC".getBytes());                                        // 거래구분
        byteBuffer.put("05".getBytes());                                        // 업무구분: 03/04/05:적립/사용/조회
        byteBuffer.put("0200".getBytes());                                      // 전문구분
        byteBuffer.put("N".getBytes());                                         // 거래형태
        byteBuffer.put("DPT0Q37740".getBytes());                                // 단말기번호
        for(int i=0; i< 4; i++) byteBuffer.put(" ".getBytes());                 // 업체정보
        for(int i=0; i<12; i++) byteBuffer.put(" ".getBytes());                 // 전문일련번호
        byteBuffer.put("S".getBytes());                                         // POS Entry Mode
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());                 // 거래 고유 번호
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());
        byteBuffer.put("9".getBytes());                                         // 암호화여부
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // S/W 모델번호
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // 장비 모델번호
        for(int i=0; i<40; i++) byteBuffer.put(" ".getBytes());                 // 암호화 정보
//        byteBuffer.put((cardNumber+ "                     ").getBytes());   // Track II (카드번호 사용)
        byteBuffer.put( (cardNumber + "                     ").getBytes());     // Track II  (사업자 번호 사용 시 KSNET Reserved 필드 "CI" / 사업자번호로 회원고유번호 조회 시 KSNET Reserved 필드 "IQ")
        byteBuffer.put((byte)0x1C);                                             // FS
        byteBuffer.put("02".getBytes());                                        // 판매구분
        byteBuffer.put("000000001000".getBytes());                              // 총금액
        byteBuffer.put("000000000000".getBytes());                              // 봉사료
        byteBuffer.put("000000000000".getBytes());                              // 세금
        byteBuffer.put("000000000000".getBytes());                              // 공급금액
        byteBuffer.put("000000000000".getBytes());                              // 면세금액
        byteBuffer.put("BB".getBytes());                                        // Working Key Index
        byteBuffer.put((pasword+"            ").getBytes());                          // 비밀번호
        byteBuffer.put("            ".getBytes());                              // 원거래승인번호
        byteBuffer.put("      ".getBytes());                                    // 원거래승인일자
        byteBuffer.put("             ".getBytes());                             // 사용자정보
        byteBuffer.put("  ".getBytes());                              			// 가맹점ID
        byteBuffer.put("                              ".getBytes());            // 가맹점사용필드
        byteBuffer.put("RED ".getBytes());                              		// Reserved
        byteBuffer.put("                    ".getBytes());                      // KSNET Reserved (사업자 번호로 조회)
        byteBuffer.put("    ".getBytes());
        for(int i=0; i<94; i++) byteBuffer.put(" ".getBytes());                 // 동글구분 ~ DCC 환율조회 Data
        byteBuffer.put("N".getBytes());                                         // 전자서명 유무
        byteBuffer.put((byte)0x03);                                            // ETX
        byteBuffer.put((byte)0x0D);                                             // CR
        return byteBuffer;
    }

    // 포인트 사용
    public ByteBuffer UsePoint(String cardNumber, String pasword, int price) {
        String mPrice = price +"";
        String tax = "";
        double mvat = Math.round((price / 1.1) * 0.1);        // 부가세(VAT)
        double mprice = Math.round(price - mvat); // 부가세제외(반올림)
        int priceLength = 12;
        String vat = (int) mvat + "";
        String myleft = (int) mprice + "";
        String totalzero = "";
        String taxzero = "";
        String leftzero = "";
        for(int i =0; i < 12; i++){
                if(i < 12 - mPrice.length()){
                    totalzero += "0";
                }
                if(i < 12 - vat.length()){
                    taxzero += "0";
                }
                if(i < 12 - myleft.length()){
                    leftzero += "0";
                }
        }

        mPrice = totalzero + mPrice;
        vat = taxzero + vat;
        myleft = leftzero + myleft;

        byteBuffer = ByteBuffer.allocateDirect(4096);
        byteBuffer.put((byte)0x02);                                             // STX
        byteBuffer.put("PC".getBytes());                                        // 거래구분
        byteBuffer.put("04".getBytes());                                        // 업무구분: 03/04/05:적립/사용/조회
        byteBuffer.put("0200".getBytes());                                      // 전문구분
        byteBuffer.put("N".getBytes());                                         // 거래형태
        byteBuffer.put("DPT0Q37742".getBytes());                                // 단말기번호
        for(int i=0; i< 4; i++) byteBuffer.put(" ".getBytes());                 // 업체정보
        for(int i=0; i<12; i++) byteBuffer.put(" ".getBytes());                 // 전문일련번호
        byteBuffer.put("S".getBytes());                                         // POS Entry Mode
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());                 // 거래 고유 번호
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());
        byteBuffer.put("9".getBytes());                                         // 암호화여부
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // S/W 모델번호
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // 장비 모델번호
        for(int i=0; i<40; i++) byteBuffer.put(" ".getBytes());                 // 암호화 정보
        byteBuffer.put((cardNumber+ "                     ").getBytes());   // Track II (카드번호 사용)
//        byteBuffer.put("2582100002219426                     ".getBytes());     // Track II  (사업자 번호 사용 시 KSNET Reserved 필드 "CI" / 사업자번호로 회원고유번호 조회 시 KSNET Reserved 필드 "IQ")
        byteBuffer.put((byte)0x1C);                                             // FS
        byteBuffer.put("02".getBytes());                                        // 판매구분
        byteBuffer.put(mPrice.getBytes());                              // 총금액   //1500 000000000000 000000000136 000000001364 000000000000BB1111
        byteBuffer.put("000000000000".getBytes());                              // 봉사료
        byteBuffer.put(vat.getBytes());                              // 세금
        byteBuffer.put(myleft.getBytes());                              // 공급금액
        byteBuffer.put("000000000000".getBytes());                              // 면세금액
        byteBuffer.put("BB".getBytes());                                        // Working Key Index
        byteBuffer.put( (pasword + "            ").getBytes());                          // 비밀번호
//        byteBuffer.put("5366837695  ".getBytes());                              // 원거래승인번호 test
        byteBuffer.put("            ".getBytes());                              // 원거래승인번호
//        byteBuffer.put("210422".getBytes());                                    // 원거래승인일자 test
        byteBuffer.put("      ".getBytes());                                    // 원거래승인일자
        byteBuffer.put("             ".getBytes());                             // 사용자정보
        byteBuffer.put("  ".getBytes());                              			// 가맹점ID
        byteBuffer.put("                              ".getBytes());            // 가맹점사용필드
        byteBuffer.put("RED ".getBytes());                              		// Reserved
        byteBuffer.put("                    ".getBytes());                      // KSNET Reserved (사업자 번호로 조회)
        byteBuffer.put("    ".getBytes());
        for(int i=0; i<94; i++) byteBuffer.put(" ".getBytes());                 // 동글구분 ~ DCC 환율조회 Data
        byteBuffer.put("N".getBytes());                                         // 전자서명 유무
        byteBuffer.put((byte)0x03);                                            // ETX
        byteBuffer.put((byte)0x0D);                                             // CR
        return byteBuffer;
    }

    public ByteBuffer Cancel_Point(String cardNumber, String pasword, int price, String ApprovalNo, String ApprovalDate) {
        String mPrice = price +"";
        String tax = "";
        double mvat = Math.round((price / 1.1) * 0.1);        // 부가세(VAT)
        double mprice = Math.round(price - mvat); // 부가세제외(반올림)
        int priceLength = 12;
        String vat = (int) mvat + "";
        String myleft = (int) mprice + "";
        String totalzero = "";
        String taxzero = "";
        String leftzero = "";
        for(int i =0; i < 12; i++){
            if(i < 12 - mPrice.length()){
                totalzero += "0";
            }
            if(i < 12 - vat.length()){
                taxzero += "0";
            }
            if(i < 12 - myleft.length()){
                leftzero += "0";
            }
        }
        String redDate = ApprovalDate.substring(0,6);

        mPrice = totalzero + mPrice;
        vat = taxzero + vat;
        myleft = leftzero + myleft;

        byteBuffer = ByteBuffer.allocateDirect(4096);
        byteBuffer.put((byte)0x02);                                             // STX
        byteBuffer.put("PC".getBytes());                                        // 거래구분
        byteBuffer.put("04".getBytes());                                        // 업무구분: 03/04/05:적립/사용/조회
        byteBuffer.put("0420".getBytes());                                      // 전문구분
        byteBuffer.put("N".getBytes());                                         // 거래형태
        byteBuffer.put("DPT0Q37742".getBytes());                                // 단말기번호
        for(int i=0; i< 4; i++) byteBuffer.put(" ".getBytes());                 // 업체정보
        for(int i=0; i<12; i++) byteBuffer.put(" ".getBytes());                 // 전문일련번호
        byteBuffer.put("S".getBytes());                                         // POS Entry Mode
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());                 // 거래 고유 번호
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());
        byteBuffer.put("9".getBytes());                                         // 암호화여부
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // S/W 모델번호
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // 장비 모델번호
        for(int i=0; i<40; i++) byteBuffer.put(" ".getBytes());                 // 암호화 정보
        byteBuffer.put((cardNumber+ "                     ").getBytes());   // Track II (카드번호 사용)
//        byteBuffer.put("2582100002219426                     ".getBytes());     // Track II  (사업자 번호 사용 시 KSNET Reserved 필드 "CI" / 사업자번호로 회원고유번호 조회 시 KSNET Reserved 필드 "IQ")
        byteBuffer.put((byte)0x1C);                                             // FS
        byteBuffer.put("02".getBytes());                                        // 판매구분
        byteBuffer.put(mPrice.getBytes());                              // 총금액   //1500 000000000000 000000000136 000000001364 000000000000BB1111
        byteBuffer.put("000000000000".getBytes());                              // 봉사료
        byteBuffer.put(vat.getBytes());                              // 세금
        byteBuffer.put(myleft.getBytes());                              // 공급금액
        byteBuffer.put("000000000000".getBytes());                              // 면세금액
        byteBuffer.put("BB".getBytes());                                        // Working Key Index
        byteBuffer.put( (pasword + "            ").getBytes());                          // 비밀번호
        byteBuffer.put(ApprovalNo.getBytes());                              // 원거래승인번호
        byteBuffer.put(redDate.getBytes());                                    // 원거래승인일자
        byteBuffer.put("             ".getBytes());                             // 사용자정보
        byteBuffer.put("  ".getBytes());                              			// 가맹점ID
        byteBuffer.put("                              ".getBytes());            // 가맹점사용필드
        byteBuffer.put("RED ".getBytes());                              		// Reserved
        byteBuffer.put("                    ".getBytes());                      // KSNET Reserved (사업자 번호로 조회)
        byteBuffer.put("    ".getBytes());
        for(int i=0; i<94; i++) byteBuffer.put(" ".getBytes());                 // 동글구분 ~ DCC 환율조회 Data
        byteBuffer.put("N".getBytes());                                         // 전자서명 유무
        byteBuffer.put((byte)0x03);                                            // ETX
        byteBuffer.put((byte)0x0D);                                             // CR
        return byteBuffer;
    }
    // 포인트 적립
    public ByteBuffer ADD_Point() {
        byteBuffer = java.nio.ByteBuffer.allocateDirect(4096);
        byteBuffer.put((byte)0x02);                                             // STX
        byteBuffer.put("PC".getBytes());                                        // 거래구분
        byteBuffer.put("03".getBytes());                                        // 업무구분
        byteBuffer.put("0200".getBytes());                                      // 전분구분
        byteBuffer.put("N".getBytes());                                         // 거래형태
        byteBuffer.put("DPT0TEST05".getBytes());                                // 단말기번호
        for(int i=0; i< 4; i++) byteBuffer.put(" ".getBytes());                 // 업체정보
        for(int i=0; i<12; i++) byteBuffer.put(" ".getBytes());                 // 전문일련번호
        byteBuffer.put("K".getBytes());                                         // POS Entry Mode
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());                 // 거래 고유 번호
        for(int i=0; i<20; i++) byteBuffer.put(" ".getBytes());                 // 암호화하지 않은 카드 번호
        byteBuffer.put("9".getBytes());                                         // 암호화여부
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // S/W 모델번호
        for(int i=0; i<16; i++) byteBuffer.put("#".getBytes());                 // 장비 모델번호
        for(int i=0; i<40; i++) byteBuffer.put(" ".getBytes());                 // 암호화 정보
        byteBuffer.put("1208197322                           ".getBytes());     // Track II  (사업자 번호 사용 시 KSNET Reserved 필드 "CI")
        //byteBuffer.put("00000000                             ".getBytes());     // Track II  (회원고유번호로 적립 시 KSNET Reserved 필드 "ID")
        //byteBuffer.put("2581600028276103                     ".getBytes());     // Track II (카드번호 사용)
        byteBuffer.put((byte)0x1C);                                             // FS
        byteBuffer.put("01".getBytes());                                        // 판매구분
        byteBuffer.put("000000001000".getBytes());                              // 총금액
        byteBuffer.put("000000000000".getBytes());                              // 봉사료
        byteBuffer.put("000000000000".getBytes());                              // 세금
        byteBuffer.put("000000000000".getBytes());                              // 공급금액
        byteBuffer.put("000000000000".getBytes());                              // 면세금액
        byteBuffer.put("AA".getBytes());                                        // Working Key Index
        //for(int i=0; i<16; i++) byteBuffer.put("0".getBytes());                 // 비밀번호
        byteBuffer.put("1111            ".getBytes());                          // 비밀번호
        byteBuffer.put("            ".getBytes());                              // 원거래승인번호
        byteBuffer.put("      ".getBytes());                                    // 원거래승인일자
        byteBuffer.put("             ".getBytes());                             // 사용자정보
        byteBuffer.put("  ".getBytes());                              			// 가맹점ID
        byteBuffer.put("                              ".getBytes());            // 가맹점사용필드
        byteBuffer.put("BLUE".getBytes());                              		// Reserved
        byteBuffer.put("CI                  ".getBytes());                      // KSNET Reserved (사업자 번호로 적립)
        //byteBuffer.put("ID                  ".getBytes());                      // KSNET Reserved (회원고유번호로 적립)
        //byteBuffer.put("                    ".getBytes());                      // KSNET Reserved
        for(int i=0; i<94; i++) byteBuffer.put(" ".getBytes());                 // 동글구분 ~ DCC 환율조회 Data
        byteBuffer.put("N".getBytes());                                         // 전자서명 유무
        byteBuffer.put((byte)0x03);                                             // ETX
        byteBuffer.put((byte)0x0D);                                             // CR

        return byteBuffer;
    }


}
