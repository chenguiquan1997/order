<html>
    <head>
        <meta charset="utf-8">
        <title>卖家端商品列表</title>
        <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="/sell/css/style.css" rel="stylesheet">
    </head>

    <body>
        <div id="wrapper" class="toggled">
            <!--引入边栏-->
            <#include "../common/nav.ftl">
            <div id="page-content-wrapper">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-hover table-condensed table-bordered">
                                <thead>
                                    <tr>
                                        <th>商品id</th>
                                        <th>名称</th>
                                        <th>图片</th>
                                        <th>单价</th>
                                        <th>库存</th>
                                        <th>描述</th>
                                        <th>类目</th>
                                        <th>创建时间</th>
                                        <th>修改时间</th>
                                        <th colspan="2">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list productInfoPageData.getContent() as ProductInfo>
                                        <tr>
                                            <td>${ProductInfo.productId}</td>
                                            <td>${ProductInfo.productName}</td>
                                            <td><img src="${ProductInfo.productIcon}" height="100" width="100"></td>
                                            <td>${ProductInfo.productPrice}</td>
                                            <td>${ProductInfo.productStock}</td>
                                            <td>${ProductInfo.productDescription}</td>
                                            <td>${ProductInfo.productCategoryType}</td>
                                            <td>${ProductInfo.productCreateTime}</td>
                                            <td>${ProductInfo.productUpdateTime}</td>
                                            <td><a href="/sell/seller/product/productUpdateOrAddUI?productId=${ProductInfo.productId}">修改</a></td>
                                            <td>
                                                <#if ProductInfo.getProductStatusCode().message == "商品在架">
                                                    <a href="/sell/seller/product/offSale?productId=${ProductInfo.productId}">下架</a>
                                                <#else >
                                                    <a href="/sell/seller/product/onSale?productId=${ProductInfo.productId}">上架</a>
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                                <!--如果当前页数index大于1，那么可以点击“上一页的按钮”-->
                                <#if currentPageData lte 1>
                                    <li class="disabled"><a href="#">上一页</a></li>
                                <#else >
                                    <li><a href="/sell/seller/product/searchAllProductBySeller?page=${currentPageData - 1}&size=${currentSizeData}">上一页</a></li>
                                </#if>
                                <#list 1..productInfoPageData.getTotalPages() as index>
                                    <#if currentPageData == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/product/searchAllProductBySeller?page=${index}&size=${currentSizeData}">${index}</a></li>
                                    </#if>
                                </#list>
                                <#if currentPageData gte productInfoPageData.getTotalPages()>
                                    <li class="disabled"><a href="#">下一页</a></li>
                                <#else >
                                    <li><a href="/sell/seller/product/searchAllProductBySeller?page=${currentPageData + 1}&size=${currentSizeData}">下一页</a></li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>