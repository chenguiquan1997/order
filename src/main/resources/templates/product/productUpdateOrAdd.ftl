<html>
    <head>
        <meta charset="utf-8">
        <title>修改/添加商品</title>
        <!--rel="stylesheet的意思是：告诉程序当前引用的资源是从外部加载的"-->
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
                            <form role="form" method="post" action="/sell/seller/product/productUpdateOrAddByForm">

                                <div class="form-group">
                                    <label>名称</label>
                                    <input name="productName" type="text" class="form-control" value="${(productInfoData.productName)!''}"/>
                                </div>

                                <div class="form-group">
                                    <label>价格</label>
                                    <input name="productPrice" type="text" class="form-control" value="${(productInfoData.productPrice)!''}"/>
                                </div>

                                <div class="form-group">
                                    <label>库存</label>
                                    <input name="productStock" type="number" class="form-control" value="${(productInfoData.productStock)!''}"/>
                                </div>

                                <div class="form-group">
                                    <label>描述</label>
                                    <input name="productDescription" type="text" class="form-control" value="${(productInfoData.productDescription)!''}"/>
                                </div>

                                <div class="form-group">
                                    <label>图片</label>
                                    <img src="${(productInfoData.productIcon)!''}" height="100" width="100">
                                    <input name="productIcon" type="text" class="form-control" value="${(productInfoData.productIcon)!''}"/>
                                </div>

                                <div class="form-group">
                                    <label>类目</label>
                                    <!-- 注意：form表单中，用于传入数据的标签，其中的name属性值一定要跟（你要注入数据中的属性名相同）-->
                                    <select name="productCategoryType" class="form-control">
                                        <!--在freemarker里面，??代表不等于空的意思-->
                                        <#list productCategoryListData as productCategory>
                                            <option value="${productCategory.productCategoryType}"
                                                    <#if (productInfoData.productId) ?? && productInfoData.productCategoryType == productCategory.productCategoryType>
                                                         selected
                                                    </#if>
                                                >${productCategory.categoryName}
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                                <!--!''在freemarker中的作用是：当没有参数传入时，默认为null-->
                                <input type="text" name="productId" value="${(productInfoData.productId)!''}">
                                <button type="submit" class="btn btn-default">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
