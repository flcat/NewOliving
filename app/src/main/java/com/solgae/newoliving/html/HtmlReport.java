package com.solgae.newoliving.html;

import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.vo.ApprovalReport;

import java.text.DecimalFormat;

public class HtmlReport {

    DecimalFormat decimalFormat;

    String html_data =
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>A simple, clean, and responsive HTML invoice template</title>\n" +
            "\n" +
            "    <style>\n" +
            "    .invoice-box {\n" +
            "        max-width: 500px;\n" +
            "        margin: auto;\n" +
            "        padding: 30px;\n" +
            "        border: 1px solid #eee;\n" +
            "        box-shadow: 0 0 10px rgba(0, 0, 0, .15);\n" +
            "        font-size: 16px;\n" +
            "        line-height: 24px;\n" +
            "        font-family: \\'Helvetica Neue\\', \\'Helvetica\\', Helvetica, Arial, sans-serif;\n" +
            "        color: #555;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table {\n" +
            "        width: 100%;\n" +
            "        line-height: inherit;\n" +
            "        text-align: left;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table td {\n" +
            "        padding: 5px;\n" +
            "        vertical-align: top;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr td:nth-child(2) {\n" +
            "        text-align: left;\n" +
            "    }\n" +
            "\n" +
            "     .invoice-box table tr.heading td:nth-child(3) {\n" +
            "        text-align: center;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.top table td {\n" +
            "        padding-bottom: 20px;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.top td.title {\n" +
            "        font-size: 45px;\n" +
            "        line-height: 45px;\n" +
            "\t\ttext-align: center;\n" +
            "        color: #333;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.information table td {\n" +
            "        padding-bottom: 10px;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.heading tr {\n" +
            "\t\tweight: 100px;\n" +
            "\t}\n" +
            "\n" +
            "    .invoice-box table tr td:nth-child(1) {\n" +
            "        text-align: left;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr td:nth-child(2) {\n" +
            "        text-align: right;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.heading td {\n" +
            "        background: #eee;\n" +
            "        border-bottom: 1px solid #ddd;\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.details td {\n" +
            "        padding-bottom: 20px;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.heading td:nth-child(2){\n" +
            "                text-align: center;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.heading td:nth-child(3){\n" +
            "                text-align: center;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.heading td:nth-child(4){\n" +
            "                text-align: center;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.item td:nth-child(3){\n" +
            "                text-align: center;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.item td:nth-child(4){\n" +
            "                text-align: right;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.total_detail.table td:nth-child(3){\n" +
            "                text-align: left;\n" +
            "    }\n" +
            "    .invoice-box table tr.total_detail td.total_detail_bill{\n" +
            "                text-align: right;\n" +
            "              font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.total_detail td.total_detail_bill_title{\n" +
            "            font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.total_detail:nth-child(2) {\n" +
            "\t\tborder-bottom: 1px solid #eee;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.item td{\n" +
            "        border-bottom: 1px solid #eee;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.heading td {\n" +
            "        background: #eee;\n" +
            "        border-bottom: 1px solid #ddd;\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "\t.invoice-box table tr.total td:nth-child(1){\n" +
            "        border-bottom: 1px solid #ddd;\n" +
            "    }\n" +
            "\t.invoice-box table tr.total td:nth-child(2){\n" +
            "        border-bottom: 1px solid #ddd;\n" +
            "    }\n" +
            "    .invoice-box table tr.total_detail td:nth-child(4){\n" +
            "                text-align: right;\n" +
            "    }\n" +
            "\t.invoice-box tr.border_bottom_line td:nth-child(1){\n" +
            "\t\ttext-align: left;\n" +
            "\t\t\tborder-bottom: 1px solid #eee;\n" +
            "\t\tfont-weight: bold;\n" +
            "    }\n" +
            "    .invoice-box table tr.total_detail table tr.border_bottom td:nth-child(1){\n" +
            "                text-align: left;\n" +
            "            \t\tborder-bottom: 1px solid #eee;\n" +
            "                font-weight: bold;\n" +
            "    }\n" +
            "   .invoice-box table tr.total_detail table tr.border_bottom td:nth-child(2){\n" +
            "                text-align: left;\n" +
            "            \t\tborder-bottom: 1px solid #eee;\n" +
            "                font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "   .invoice-box table tr.total_detail table tr.border_bottom td:nth-child(3){\n" +
            "               text-align: right;\n" +
            "            \t\tborder-bottom: 1px solid #eee;\n" +
            "              font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .invoice-box table tr.total_detail td:nth-child(2){\n" +
            "                text-align: right;\n" +
            "    }\n" +
            "\n" +
            "\t.invoice-box table tr.title td{\n" +
            "\t\t\ttext-align: center;\n" +
            "    }\n" +
            "    @media only screen and (max-width: 500px) {\n" +
            "        .invoice-box table tr.top table td {\n" +
            "            width: 100%;\n" +
            "            display: block;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "\n" +
            "        .invoice-box table tr.information table td {\n" +
            "            width: 100%;\n" +
            "            display: block;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    /** RTL **/\n" +
            "    .rtl {\n" +
            "        direction: rtl;\n" +
            "        font-family: Tahoma, \\'Helvetica Neue\\', \\'Helvetica\\', Helvetica, Arial, sans-serif;\n" +
            "    }\n" +
            "\n" +
            "    .rtl table {\n" +
            "        text-align: right;\n" +
            "    }\n" +
            "\n" +
            "    .rtl table tr td:nth-child(4) {\n" +
            "        text-align: left;\n" +
            "    }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"invoice-box\">\n" +
            "        <table cellpadding=\"0\" cellspacing=\"0\">\n" +
            "            <tr class=\"top\">\n" +
            "                <td colspan=\"4\">\n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td class=\"image\">\n" +
            "                                <img src=\"http://211.219.15.88:7150/xplogo.png\" style=\"width:50; max-width:50px;\">\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "\t\t\t\t\t\t<tr>\n" +
            "                            <td class=\"title\">\n" +
            "                                영 수 증\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "            <tr class=\"information\">\n" +
            "                <td colspan=\"4\">\n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td>\n" +
            "                                사업자명<br>\n" +
            "                                사업자번호<br>\n" +
            "                                대표자명<br>\n" +
            "                                전화번호<br>\n" +
            "                                주   소<br>\n" +
            "                                테이블명<br>\n" +
            "                                주문번호\n" +
            "                            </td>\n" +
            "\n" +
            "                            <td>\n" +
            "                                엑스페론(주)<br>\n" +
            "                                000-00-0000<br>\n" +
            "                                김영준<br>\n" +
            "                                000-000-000<br>\n" +
            "\t\t\t\t\t\t\t\t서울특별시 강남구 율현동 밤고개로24길(율현동 56)<br>\n" +
            "\t\t\t\t\t\t\t\t203<br>\n" +
            "\t\t\t\t\t\t\t\t200819 01 00001\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "            <tr class=\"heading\">\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t품명\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t단가\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t수량\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t금액\n" +
            "\t\t\t\t</td>\n" +
            "            </tr>\n" +
            "\n" +
            "            <tr class=\"item\">\n" +
            "                <td>\n" +
            "                    Check2222222222222\n" +
            "                </td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t1\n" +
            "\t\t\t\t</td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "\n" +
            "            <tr class=\"item\">\n" +
            "                <td>\n" +
            "                    Check2222222222222\n" +
            "                </td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t1\n" +
            "\t\t\t\t</td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "\n" +
            "            <tr class=\"item\">\n" +
            "                <td>\n" +
            "                    Check2222222222222\n" +
            "                </td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t1\n" +
            "\t\t\t\t</td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "\n" +
            "            <tr class=\"item\">\n" +
            "                <td>\n" +
            "                    Check2222222222222\n" +
            "                </td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t1\n" +
            "\t\t\t\t</td>\n" +
            "                <td>\n" +
            "                    1,000,000\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "            <tr class=\"total\" colspan=\"4\">\n" +
            "\t\t\t\t<td colspan=\"2\">\n" +
            "\t\t\t\t\t주문합계<br>\n" +
            "\t\t\t\t\t할인금액<br>\n" +
            "\t\t\t\t\t받을금액\n" +
            "\t\t\t\t</td>\n" +
            "\n" +
            "\t\t\t\t<td colspan=\"2\">\n" +
            "\t\t\t\t\t10,000<br>\n" +
            "\t\t\t\t\t0<br>\n" +
            "\t\t\t\t\t10,000\n" +
            "\t\t\t\t</td>\n" +
            "            </tr>\n" +
            "\n" +
            "            <tr class=\"total_detail\">\n" +
            "                <td colspan=\"4\">\n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td>\n" +
            "                                현금<br>\n" +
            "                                카드<br>\n" +
            "                                포인트\n" +
            "                            </td>\n" +
            "                            <td>\n" +
            "                                0<br>\n" +
            "                                0<br>\n" +
            "                                0\n" +
            "                          <td>\n" +
            "                                과세<br>\n" +
            "                                세액<br>\n" +
            "                                면세\n" +
            "                            </td>\n" +
            "                          <td text-align=\"right\">\n" +
            "                                0<br>\n" +
            "                                0<br>\n" +
            "                                0\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "\t\t\t\t\t\t<tr class=\"border_bottom_line\">\n" +
            "\t\t\t\t\t\t\t<td colspan=\"4\"/>\n" +
            "\t\t\t\t\t\t</tr>\n" +
            "                        <tr class=\"border_bottom\">\n" +
            "                          \t<td colspan=\"2\">\n" +
            "                \t\t\t<td>영수금액</td>\n" +
            "                \t\t\t<td>0</td>\n" +
            "            \t\t\t\t</tr>\n" +
            "                    </table>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "\n" +
            "\t\t\t<tr class=\"title\">\n" +
            "                <td colspan=\"4\">\n" +
            "                  [국민비씨카드 신용승인]\n" +
            "                </td>\n" +
            "          </tr>\n" +
            "\n" +
            "          <tr>\n" +
            "            <td   colspan=\"4\">\n" +
            "              <table>\n" +
            "                <tr>\n" +
            "                  \t<td>\n" +
            "                      승인일시<br>\n" +
            "                      카드번호<br>\n" +
            "                      승인번호<br>\n" +
            "                      승인금액<br>\n" +
            "                      가맹번호<br>\n" +
            "                      사업자번호\n" +
            "              \t\t</td>\n" +
            "                  \t<td>\n" +
            "                      2020-08-20 17:49:00<br>\n" +
            "                      625***********78<br>\n" +
            "                      7559000<br>\n" +
            "                      10,000<br>\n" +
            "                      7164563330<br>\n" +
            "                      000\n" +
            "                \t</td>\n" +
            "                </tr>\n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"4\">\n" +
            "              50,000 이하 무서명 거래입니다.\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr class=\"border_bottom_line\">\n" +
            "            <td colspan=\"4\"/>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"2\">\n" +
            "              2020-07-24 17:49 CASHIER :\n" +
            "            </td>\n" +
            "            <td colspan=\"2\">\n" +
            "              Bill 0209010101011011010100\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n";


    String Header = "<head>\n" +
            "            <meta charset=\"utf-8\">\n" +
            "            <title>A simple, clean, and responsive HTML invoice template</title>\n" +
            "\n" +
            "            <style>\n" +
            "            .invoice-box {\n" +
            "                max-width: 500px;\n" +
            "                margin: auto;\n" +
            "                padding: 30px;\n" +
            "                border: 1px solid #eee;\n" +
            "                box-shadow: 0 0 10px rgba(0, 0, 0, .15);\n" +
            "                font-size: 16px;\n" +
            "                line-height: 24px;\n" +
            "                font-family: \\'Helvetica Neue\\', \\'Helvetica\\', Helvetica, Arial, sans-serif;\n" +
            "                color: #555;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table {\n" +
            "                width: 100%;\n" +
            "                line-height: inherit;\n" +
            "                text-align: left;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table td {\n" +
            "                padding: 5px;\n" +
            "                vertical-align: top;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr td:nth-child(2) {\n" +
            "                text-align: left;\n" +
            "            }\n" +
            "\n" +
            "             .invoice-box table tr.heading td:nth-child(3) {\n" +
            "                text-align: center;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.top table td {\n" +
            "                padding-bottom: 20px;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.top td.title {\n" +
            "                font-size: 45px;\n" +
            "                line-height: 45px;\n" +
            "                text-align: center;\n" +
            "                color: #333;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.information table td {\n" +
            "                padding-bottom: 10px;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.heading tr {\n" +
            "                weight: 100px;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr td:nth-child(1) {\n" +
            "                text-align: left;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr td:nth-child(2) {\n" +
            "                text-align: right;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.heading td {\n" +
            "                background: #eee;\n" +
            "                border-bottom: 1px solid #ddd;\n" +
            "                font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.details td {\n" +
            "                padding-bottom: 20px;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.heading td:nth-child(2){\n" +
            "                        text-align: center;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.heading td:nth-child(3){\n" +
            "                        text-align: center;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.heading td:nth-child(4){\n" +
            "                        text-align: center;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.item td:nth-child(3){\n" +
            "                        text-align: center;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.item td:nth-child(4){\n" +
            "                        text-align: right;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.total_detail.table td:nth-child(3){\n" +
            "                        text-align: left;\n" +
            "            }\n" +
            "            .invoice-box table tr.total_detail td.total_detail_bill{\n" +
            "                        text-align: right;\n" +
            "                      font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.total_detail td.total_detail_bill_title{\n" +
            "                    font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.total_detail:nth-child(2) {\n" +
            "                border-bottom: 1px solid #eee;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.item td{\n" +
            "                border-bottom: 1px solid #eee;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.heading td {\n" +
            "                background: #eee;\n" +
            "                border-bottom: 1px solid #ddd;\n" +
            "                font-weight: bold;\n" +
            "            }\n" +
            "            .invoice-box table tr.total td:nth-child(1){\n" +
            "                border-bottom: 1px solid #ddd;\n" +
            "            }\n" +
            "            .invoice-box table tr.total td:nth-child(2){\n" +
            "                border-bottom: 1px solid #ddd;\n" +
            "            }\n" +
            "            .invoice-box table tr.total_detail td:nth-child(4){\n" +
            "                        text-align: right;\n" +
            "            }\n" +
            "            .invoice-box tr.border_bottom_line td:nth-child(1){\n" +
            "                text-align: left;\n" +
            "                    border-bottom: 1px solid #eee;\n" +
            "                font-weight: bold;\n" +
            "            }\n" +
            "            .invoice-box table tr.total_detail table tr.border_bottom td:nth-child(1){\n" +
            "                        text-align: left;\n" +
            "                            border-bottom: 1px solid #eee;\n" +
            "                        font-weight: bold;\n" +
            "            }\n" +
            "           .invoice-box table tr.total_detail table tr.border_bottom td:nth-child(2){\n" +
            "                        text-align: left;\n" +
            "                            border-bottom: 1px solid #eee;\n" +
            "                        font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "           .invoice-box table tr.total_detail table tr.border_bottom td:nth-child(3){\n" +
            "                       text-align: right;\n" +
            "                            border-bottom: 1px solid #eee;\n" +
            "                      font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.total_detail td:nth-child(2){\n" +
            "                        text-align: right;\n" +
            "            }\n" +
            "\n" +
            "            .invoice-box table tr.title td{\n" +
            "                    text-align: center;\n" +
            "            }\n" +
            "            @media only screen and (max-width: 500px) {\n" +
            "                .invoice-box table tr.top table td {\n" +
            "                    width: 100%;\n" +
            "                    display: block;\n" +
            "                    text-align: center;\n" +
            "                }\n" +
            "\n" +
            "                .invoice-box table tr.information table td {\n" +
            "                    width: 100%;\n" +
            "                    display: block;\n" +
            "                    text-align: center;\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            /** RTL **/\n" +
            "            .rtl {\n" +
            "                direction: rtl;\n" +
            "                font-family: Tahoma, \\'Helvetica Neue\\', \\'Helvetica\\', Helvetica, Arial, sans-serif;\n" +
            "            }\n" +
            "\n" +
            "            .rtl table {\n" +
            "                text-align: right;\n" +
            "            }\n" +
            "\n" +
            "            .rtl table tr td:nth-child(4) {\n" +
            "                text-align: left;\n" +
            "            }\n" +
            "            </style>\n" +
            "        </head>";
    String BodyST = "<body>";
    String BodyED = "</body>";
    String DivST = "<div class=\"invoice-box\">";
    String DivED = "</div>";
    String TableST = "<table cellpadding=\"0\" cellspacing=\"0\">";
    String TableED = "</table>";

    String Tr_Top = "<tr class=\"top\">\n" +
            "                <td colspan=\"4\">\n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td class=\"image\">\n" +
            "                                <img src=\"%1$s\" style=\"width:50; max-width:50px;\">\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "\t\t\t\t\t\t<tr>   \n" +
            "                            <td class=\"title\">\n" +
            "                                영 수 증\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                </td>\n" +
            "            </tr>";

    String Tr_Information = "            <tr class=\"information\">\n" +
            "                <td colspan=\"4\">\n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td>\n" +
            "                                사업자명<br>\n" +
            "                                사업자번호<br>\n" +
            "                                대표자명<br>\n" +
            "                                전화번호<br>\n" +
            "                                주   소<br>\n" +
            "                                테이블명<br>\n" +
            "                                주문번호\n" +
            "                            </td>\n" +
            "                            \n" +
            "                            <td>\n" +
            "                                %1$s<br>\n" +                                   //%1$s  엑스페론(주)
            "                                %2$s<br>\n" +                                   //      사업자번호
            "                                %3$s<br>\n" +                                   //       대표자명
            "                                %4$s<br>\n" +                                   //      전화번호
            "\t\t\t\t\t\t\t\t%5$s)<br>\n" +                                                    //       주소
            "\t\t\t\t\t\t\t\t%6$s<br>\n" +                                                     //      테이블번호
            "\t\t\t\t\t\t\t\t%7$s\n" +                                                          //       주문번호
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                </td>\n" +
            "            </tr>";

    String Tr_List_Heading = "<tr class=\"heading\">\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t품명\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t단가\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t수량\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t금액\n" +
            "\t\t\t\t</td>\t\t\t\n" +
            "</tr>";

    String Tr_List_Value = "            <tr class=\"item\">\n" +
            "                <td>\n" +
            "                    %1$s\n" + // 품명
            "                </td>\n" +
            "                <td>\n" +
            "                    %2$s\n" +            // 단가
            "                </td>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t%3$s\n" +                                 // 수량
            "\t\t\t\t</td>\n" +
            "                <td>\n" +
            "                    %4$s\n" +            // 금액
            "                </td>\t\t\t\t\n" +
            "            </tr>";

    String Tr_Total = "<tr class=\"total\" colspan=\"4\">\n" +
            "\t\t\t\t<td colspan=\"2\">\n" +
            "\t\t\t\t\t주문합계<br>\n" +
            "\t\t\t\t\t할인금액<br>\n" +
            "\t\t\t\t\t받을금액\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t<td colspan=\"2\">\n" +
            "\t\t\t\t\t%1$s<br>\n" +
            "\t\t\t\t\t%2$s<br>\n" +
            "\t\t\t\t\t%3$s\n" +
            "\t\t\t\t</td>\n" +
            "            </tr>";

    String Tr_Total_Detail = "            <tr class=\"total_detail\">\n" +
            "                <td colspan=\"4\">\n" +
            "                    <table>\n" +
            "                        <tr>\n" +
            "                            <td>\n" +
            "                                현금<br>\n" +
            "                                카드<br>\n" +
            "                                포인트\n" +
            "                            </td>                            \n" +
            "                            <td>\n" +
            "                                %1$s<br>\n" +
            "                                %2$s<br>\n" +
            "                                %3$s\n" +
            "                          <td>\n" +
            "                                과세<br>\n" +
            "                                세액<br>\n" +
            "                                면세\n" +
            "                            </td>\n" +
            "                          <td text-align=\"right\">\n" +
            "                                %4$s<br>\n" +
            "                                %5$s<br>\n" +
            "                                %6$s\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "\t\t\t\t\t\t<tr class=\"border_bottom_line\">\n" +
            "\t\t\t\t\t\t\t<td colspan=\"4\"/>\n" +
            "\t\t\t\t\t\t</tr>\n" +
            "                        <tr class=\"border_bottom\">\n" +
            "                          \t<td colspan=\"2\">\n" +
            "                \t\t\t<td>영수금액</td>\n" +
            "                \t\t\t<td>%7$s</td>\n" +
            "            \t\t\t\t</tr>            \n" +
            "                    </table>\n" +
            "                </td>\n" +
            "            </tr>";

    String Tr_Approval_Title = "\t\t\t<tr class=\"title\">\n" +
            "                <td colspan=\"4\">\n" +
            "                  [%1$s 신용승인]\n" +                      // 카드발급사
            "                </td>\n" +
            "          </tr> ";

    String Tr_Approal_List = "          <tr>\n" +
            "            <td   colspan=\"4\">\n" +
            "              <table>\n" +
            "                <tr>\n" +
            "                  \t<td>\n" +
            "                      승인일시<br>\n" +
            "                      카드번호<br>\n" +
            "                      승인번호<br>\n" +
            "                      승인금액<br>\n" +
            "                      가맹번호<br>\n" +
            "                      사업자번호\t\t\t\t\t\t\n" +
            "              \t\t</td>\n" +
            "                  \t<td>\n" +
            "                      %1$s<br>\n" +
            "                      %2$s<br>\n" +
            "                      %3$s<br>\n" +
            "                      %4$s<br>\n" +
            "                      %5$s<br>\n" +
            "                      %6$s\t\t\t\t\t\t\n" +
            "                \t</td>\n" +
            "                </tr>\n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr> " +
            "          <tr>\n" +
            "            <td colspan=\"4\">\n" +
            "              50,000 이하 무서명 거래입니다.\n" +
            "            </td>\t\t\t\n" +
            "          </tr>" +
            "          <tr class=\"border_bottom_line\">\n" +
            "            <td colspan=\"4\"/>\n" +
            "          </tr>";

    String App_Position = "          <tr>\n" +
            "            <td colspan=\"2\">\n" +
            "              %1$s CASHIER :\n" +
            "            </td>\t\t\n" +
            "            <td colspan=\"2\">\n" +
            "              %2$s\n" +
            "            </td>\t\n" +
            "          </tr>  ";

    String Tr_ED = "</tr>";

    public HtmlReport() {
        decimalFormat = new DecimalFormat("###,###");
    }

    public String mStrData(ApprovalReport approvalReport) {



        String html  = "<html>";
               html += Header;
               html += BodyST;
               html += DivST;
               html += TableST;
               html += String.format(Tr_Top, "http://211.219.15.88:7150/xplogo.png"); // Tr_Top

// 사업자명, 사업자번호, 대표자명, 전화번호, 주소, 테이블번호, 주문번호
               html += String.format(Tr_Information,
                       approvalReport.getSaupjaName(),
                       approvalReport.getSaupjaNo(),
                       approvalReport.getDaepyoName(),
                       approvalReport.getPhoneNumber(),
                       approvalReport.getAddress(),
                       approvalReport.getTableNo(),
                       approvalReport.getOrderNumber());

               html += Tr_List_Heading;
// 품명, 수량, 단가, 금액
               for (int i = 0; i<approvalReport.getOrderitems().size(); i++) {
                   html += String.format(Tr_List_Value,
                           approvalReport.getOrderitems().get(i).getItemname(),
                           decimalFormat.format(approvalReport.getOrderitems().get(i).getItemdanga()),
                           decimalFormat.format(approvalReport.getOrderitems().get(i).getItemqty()),
                           decimalFormat.format(approvalReport.getOrderitems().get(i).getItemcost()));
               }
// 주문합계, 할인금액, 받을금액
               html += String.format(Tr_Total,
                       decimalFormat.format(approvalReport.getOrderTotal()),
                       decimalFormat.format(approvalReport.getSaleCost()),
                       decimalFormat.format(approvalReport.getChangeCost()));
// 현금, 카드, 포인트, 과세, 세액, 면세, 영수금액
               html += String.format(Tr_Total_Detail, approvalReport.getCash(), approvalReport.getCard(), approvalReport.getPoint(),
                       approvalReport.getTaxation(), approvalReport.getTaxamount(), approvalReport.getTaxfree(), approvalReport.getReceipt_Amount());

               html += String.format(Tr_Approval_Title, approvalReport.getCard_office());

               html += String.format(Tr_Approal_List,
                       approvalReport.getApproval_date_time(),
                       approvalReport.getCard_number(),
                       approvalReport.getApproval_number(),
                       approvalReport.getApproval_cost(),
                       approvalReport.getAffiliate(),
                       approvalReport.getSaupjaNo()
                       );
               html += String.format(App_Position, approvalReport.getApproval_date_time(), InfintiApplication.serial_number);
               html += TableED;
               html += DivED;
               html += BodyED;
               html += "</html>";
        return html;
    }
}
