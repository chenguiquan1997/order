<html>
    <head>
        <meta charset="utf-8">
        <title>卖家商品类目添加/修改</title>
        <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="/sell/css/style.css">
    </head>
    <body>
        <div id="wrapper" class="toggled">
            <!--引入边栏-->
            <#include "../common/nav.ftl">
            <div id="page-content-wrapper">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" action="/sell/seller/category/categoryUpdateOrAdd" method="post">
                                <div class="form-group">
                                    <label >商品类目名称</label>
                                    <input type="text" class="form-control" name="categoryName" value="${(productCategoryData.getCategoryName())!''}"/>
                                </div>

                                <div class="form-group">
                                    <label >类目类型</label>
                                    <input type="number" class="form-control" name="productCategoryType" value="${(productCategoryData.getProductCategoryType())!''}"/>
                                </div>
                                <input type="text" name="categoryId" value="${(productCategoryData.getCategoryId())!''}">
                                <button type="submit" class="btn btn-default">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>