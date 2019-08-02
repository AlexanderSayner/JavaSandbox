package sayner.sandbox.dto;

public enum StatusEnum {

    AllDoneWell,

    ArticleNotFound,
    ArticleIdDoesNotExist,
    ArticleEntityIsNull,
    ArticleHasNoData,

    WarehouseNotFound,
    WarehouseIdDoesNotExist,
    WarehouseEntityIsNull,
    WarehouseHasNoData,

    CouldNotTransferArticleToArticleDTO,
    CouldNotTransferArticleDTOToArticle,
    CouldNotTransferWarehouseToWarehouseDTO,
    CouldNotTransferWarehouseDTOToWarehouse,
    AnyOtherShit,

    Unauthorized,
    NoAccess
}
