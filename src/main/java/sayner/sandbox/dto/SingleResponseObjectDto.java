package sayner.sandbox.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.EnumMap;

@Log4j2
public abstract class SingleResponseObjectDto {

    @Getter(value = AccessLevel.PRIVATE)
    private final EnumMap<StatusEnum, StatusCodeEnum> errorCodeEnum =
            new EnumMap<StatusEnum, StatusCodeEnum>(StatusEnum.class);

    protected SingleResponseObjectDto() {

        errorCodeEnum.put(StatusEnum.AllDoneWell,StatusCodeEnum.StatusCode6000);
        errorCodeEnum.put(StatusEnum.ArticleNotFound,StatusCodeEnum.StatusCode6001);
        errorCodeEnum.put(StatusEnum.ArticleIdDoesNotExist,StatusCodeEnum.StatusCode6002);
        errorCodeEnum.put(StatusEnum.ArticleEntityIsNull,StatusCodeEnum.StatusCode6003);
        errorCodeEnum.put(StatusEnum.ArticleHasNoData,StatusCodeEnum.StatusCode6004);
        errorCodeEnum.put(StatusEnum.WarehouseNotFound,StatusCodeEnum.StatusCode6011);
        errorCodeEnum.put(StatusEnum.WarehouseIdDoesNotExist,StatusCodeEnum.StatusCode6012);
        errorCodeEnum.put(StatusEnum.WarehouseEntityIsNull,StatusCodeEnum.StatusCode6013);
        errorCodeEnum.put(StatusEnum.WarehouseHasNoData,StatusCodeEnum.StatusCode6014);
        errorCodeEnum.put(StatusEnum.CouldNotTransferArticleToArticleDTO,StatusCodeEnum.StatusCode6101);
        errorCodeEnum.put(StatusEnum.CouldNotTransferArticleDTOToArticle,StatusCodeEnum.StatusCode6102);
        errorCodeEnum.put(StatusEnum.CouldNotTransferWarehouseToWarehouseDTO,StatusCodeEnum.StatusCode6103);
        errorCodeEnum.put(StatusEnum.CouldNotTransferWarehouseDTOToWarehouse,StatusCodeEnum.StatusCode6104);
        errorCodeEnum.put(StatusEnum.AnyOtherShit,StatusCodeEnum.StatusCode6666);
    }

    protected StatusCodeEnum getErrorCodeEnum(StatusEnum statusEnum) {
        return this.errorCodeEnum.get(statusEnum);
    }
}