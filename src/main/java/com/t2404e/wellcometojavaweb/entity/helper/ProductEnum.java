package com.t2404e.wellcometojavaweb.entity.helper;

public enum ProductEnum {
    ACTIVE(1),
    INACTIVE(0),
    DELETED(-1);

    private final int code;

    ProductEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    // Chuyển từ code (int) sang enum
    public static ProductEnum fromCode(int statusCode) {
        for (ProductEnum e : ProductEnum.values()) {
            if (e.code == statusCode) {
                return e;
            }
        }
        // Nếu không khớp thì mặc định trả về INACTIVE
        return INACTIVE;
    }

    // Chuyển từ string (tên) sang enum
    public static ProductEnum fromString(String name) {
        if (name == null) return INACTIVE;
        try {
            return ProductEnum.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INACTIVE;
        }
    }
}
