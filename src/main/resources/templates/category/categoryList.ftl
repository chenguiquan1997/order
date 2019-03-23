<html>
    <head>
        <meta charset="utf-8">
        <title>卖家商品类目列表</title>
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
                            <table class="table table-condensed table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th>类目id</th>
                                        <th>名字</th>
                                        <th>Type</th>
                                        <th>创建时间</th>
                                        <th>修改时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list productCategoryListData as category>
                                        <tr>
                                            <td>${category.getCategoryId()}</td>
                                            <td>${category.getCategoryName()}</td>
                                            <td>${category.getProductCategoryType()}</td>
                                            <td>${category.getCategoryCreateTime()}</td>
                                            <td>${category.getCategoryUpdateTime()}</td>
                                            <td>
                                                <a href="/sell/seller/category/categoryUpdateOrAddUI?categoryId=${category.getCategoryId()}">修改</a>
                                            </td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>