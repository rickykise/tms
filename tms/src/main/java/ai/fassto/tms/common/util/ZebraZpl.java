package ai.fassto.tms.common.util;

import org.apache.commons.lang3.StringUtils;

public class ZebraZpl {
    private static final String START_COMMAND = "^XA^CW1,E:KFONT3.FNT^CI28";
    private static final String END_COMMAND = "^XZ";
    private static final String WIDTH_DOT_COMMAND = "^PW";
    private static final String HEIGHT_DOT_COMMAND = "^LL";
    private static final String COMMA = ",";
    private static final String ROTATE_RIGHT = "R";
    private static final String NOT_ROTATE = "N";
    private static final String Y = "Y";
    private static final String N = "N";
    private static final String ZEBRA_FONT = "^A1";
    private static final String LOCATION_COMMAND = "^FT";
    private static final String CONTENT_START_COMMAND = "^FD";
    private static final String CONTENT_END_COMMAND = "^FS";
    private static final String BARCODE_START_COMMAND = "^BY";
    private static final String BARCODE_RATIO = "3";
    private static final String BARCODE_128_SUBSET_C_COMMAND = ">;";
    private static final String BARCODE_2_OF_5_COMMAND = "^B2";
    private static final String BARCODE_39_COMMAND = "^B3";
    private static final String CODE128_BARCODE_COMMAND = "^BC";
    private static final String GRAPHIC_BOX_COMMAND = "^GB";
    private static final String QR_CODE_START_COMMAND = "^BQN,2,9^FDMMAA";
    private static final String NEXT_LINE = "\n";

    private final StringBuilder zplCode;
    private final int widthDots;
    private final int heightDots;

    public ZebraZpl(float widthCm, float heightCm, int dpi) {
        this.zplCode = new StringBuilder();
        this.widthDots = (int) (widthCm * 0.39 * dpi);
        this.heightDots = (int) (heightCm * 0.39 * dpi);
    }

    public ZebraZpl addText(int positionX, int positionY, int fontSize, boolean rotate, String text) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(ZEBRA_FONT)
                .append(rotate ? ROTATE_RIGHT : NOT_ROTATE)
                .append(COMMA)
                .append(fontSize)
                .append(COMMA)
                .append(fontSize)
                .append(CONTENT_START_COMMAND)
                .append(text)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public ZebraZpl addBarcode2of5(int positionX, int positionY, int width, int height, boolean rotate, boolean isInterpretation, String barcodeText) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(BARCODE_START_COMMAND)
                .append(width)
                .append(COMMA)
                .append(BARCODE_RATIO)
                .append(COMMA)
                .append(height)
                .append(BARCODE_2_OF_5_COMMAND)
                .append(rotate ? ROTATE_RIGHT : NOT_ROTATE)
                .append(COMMA)
                .append(height)
                .append(COMMA)
                .append(isInterpretation ? Y : N)
                .append(CONTENT_START_COMMAND)
                .append(barcodeText)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public ZebraZpl addBarcode39(int positionX, int positionY, int width, int height, boolean rotate, boolean isInterpretation, String barcodeText) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(BARCODE_START_COMMAND)
                .append(width)
                .append(COMMA)
                .append(BARCODE_RATIO)
                .append(COMMA)
                .append(height)
                .append(BARCODE_39_COMMAND)
                .append(rotate ? ROTATE_RIGHT : NOT_ROTATE)
                .append(COMMA)
                .append(N)
                .append(COMMA)
                .append(height)
                .append(COMMA)
                .append(isInterpretation ? Y : N)
                .append(CONTENT_START_COMMAND)
                .append(barcodeText)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public ZebraZpl addBarcode128(int positionX, int positionY, int width, int height, boolean rotate, boolean isInterpretation, boolean isSubsetC, String barcodeText) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(BARCODE_START_COMMAND)
                .append(width)
                .append(COMMA)
                .append(BARCODE_RATIO)
                .append(COMMA)
                .append(CODE128_BARCODE_COMMAND)
                .append(rotate ? ROTATE_RIGHT : NOT_ROTATE)
                .append(COMMA)
                .append(height)
                .append(COMMA)
                .append(isInterpretation ? Y : N)
                .append(CONTENT_START_COMMAND)
                .append(isSubsetC ? BARCODE_128_SUBSET_C_COMMAND : "")
                .append(barcodeText)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public ZebraZpl addGraphic(int positionX, int positionY, String graphicText) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(graphicText)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public ZebraZpl addGraphicBox(int positionX, int positionY, int width, int height, int thickness) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(GRAPHIC_BOX_COMMAND)
                .append(width)
                .append(COMMA)
                .append(height)
                .append(COMMA)
                .append(thickness)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public ZebraZpl addQRCode(int positionX, int positionY, String invoiceNo) {
        this.zplCode.append(LOCATION_COMMAND)
                .append(positionX)
                .append(COMMA)
                .append(positionY)
                .append(QR_CODE_START_COMMAND)
                .append(invoiceNo)
                .append(CONTENT_END_COMMAND)
                .append(NEXT_LINE);

        return this;
    }

    public String getZplCode() {
        return START_COMMAND +
                NEXT_LINE +
                WIDTH_DOT_COMMAND +
                this.widthDots +
                NEXT_LINE +
                HEIGHT_DOT_COMMAND +
                this.heightDots +
                NEXT_LINE +
                this.zplCode +
                NEXT_LINE +
                END_COMMAND;
    }

    /**
     * zpl 코드상 줄바꿈 지원이 안되어 상품명을 | 를 기준으로 줄바꿈해서 리턴하는 함수
     * 파스토 현 정책상 모든 상품명의 리스트는 | 로 구분되어있음.
     *
     * @param startPositionX 해당 문구가 들어가는 시작점 x 좌표
     * @param startPositionY 해당 문구가 들어가는 시작점 y 좌표
     * @param addedValue     줄바꿈 처리 할 때 마다 + 되는 y 좌표, 해당 값 만큼 아래로 내려감
     * @param fontSize       해당 필드에서 사용할 폰트 사이즈
     * @param targetLength   문자열을 줄바꿈 할 기준 길이
     * @param targetRow      무한히 출력하지 않고 정해진 열 만큼 잘라서 출력해주는 결정 값
     * @param goodsName      줄바꿈 대상 상품 명
     * @param isRotate       회전여부, 90도 우측방향 회전인경우 y값이 아닌 x값을 움직여야한다.
     * @return 줄바꿈 처리 되어 처리 후 this 를 리턴
     */
    public ZebraZpl setGoodsNameTextList(int startPositionX, int startPositionY, int addedValue, int fontSize, int targetLength, int targetRow, String goodsName, boolean isRotate) {

        String[] splitGoodsName = goodsName.split("\\|");

        for (int i = 0; i < splitGoodsName.length; i++) {
            if (StringUtils.isEmpty(splitGoodsName[i])) {
                return this;
            }
            this.addText(startPositionX, startPositionY, fontSize, isRotate, this.getLimitedLengthString(targetLength, splitGoodsName[i]));
            if (i == targetRow) {
                return this;
            }

            if (isRotate) {
                startPositionX -= addedValue;
            } else {
                startPositionY += addedValue;
            }
        }
        return this;
    }

    private String getLimitedLengthString(int targetLength, String targetStr) {
        if (StringUtils.isNotEmpty(targetStr) && targetStr.length() > targetLength) {
            return targetStr.substring(0, targetLength) + "...";
        }
        return targetStr;
    }

    /**
     * zpl 코드에서 줄바꿈을 수동으로 처리하는 함수..
     * zpl 에서 제공하는 ^FB 를 현재 쓰지 못 하는 상황으로 해당 로직을 작성하였음..
     *
     * @param startPositionX Text 시작 x 좌표 값
     * @param startPositionY Text 시작 y 좌표 값
     * @param addedValue     줄바꿈 할 때마다 ZebraText 의 Y 값을 얼마나 변동할지 값
     * @param fontSize       ZebraText 에 적용할 폰트 사이즈 값
     * @param targetLength   몇의 문자열 길이마다 줄바꿈을 할 건지 값
     * @param limitRows      몇 줄까지 원하는지 값
     * @param targetStr      줄바꿈 처리를 원하는 문자열
     * @param isRotate       오른쪽으로 90도 회전여부
     */
    public ZebraZpl setLineBreakTextList(int startPositionX, int startPositionY, int addedValue, int fontSize, int targetLength, int limitRows, String targetStr, boolean isRotate) {
        int beginIdx = 0;
        int endIdx = targetLength;

        if (targetStr.length() <= endIdx) {
            this.addText(startPositionX, startPositionY, fontSize, isRotate, targetStr);
            return this;
        }

        for (int i = 0; i < limitRows; i++) {
            if (beginIdx >= targetStr.length()) {
                return this;
            }
            if (i == limitRows - 1) {
                this.addText(startPositionX, startPositionY, fontSize, isRotate, targetStr.substring(beginIdx, endIdx) + ((endIdx == targetStr.length()) ? "" : "..."));
                return this;
            }
            this.addText(startPositionX, startPositionY, fontSize, isRotate, targetStr.substring(beginIdx, endIdx));

            beginIdx = endIdx;
            endIdx += targetLength;
            if (isRotate) {
                startPositionX -= addedValue;
            } else {
                startPositionY += addedValue;
            }

            if (endIdx > targetStr.length()) {
                endIdx = targetStr.length();
            }
        }

        return this;
    }
}