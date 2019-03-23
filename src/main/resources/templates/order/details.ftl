<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>
<body>
    <div id="wrapper" class="toggled">
        <!--引入边栏-->
        <#include "../common/nav.ftl">
        <div id="page-content-wrapper">

            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>订单Id</th>
                                    <th>订单总金额</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${orderMasterDtoData.getBuyerOrderId()}</td>
                                    <td>${orderMasterDtoData.getBuyerOrderAmount()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 订单详情-->
                    <div class="col-md-12 column">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>商品Id</th>
                                    <th>商品名称</th>
                                    <th>价格</th>
                                    <th>数量</th>
                                    <th>金额</th>
                                </tr>
                            </thead>
                            <tbody>
                                    <#list orderMasterDtoData.getBuyerOrderDetailsList() as orderDetails>
                                    <tr>
                                        <td>${orderDetails.productId}</td>
                                        <td>${orderDetails.productName}</td>
                                        <td>${orderDetails.productPrice}</td>
                                        <td>${orderDetails.productQuantity}</td>
                                        <td>${orderDetails.productQuantity * orderDetails.productPrice}</td>
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
                        <#if orderMasterDtoData.getOrderStatusByCode().message == "新订单">
                            <a href="/sell/seller/finishOrderBySeller?orderId=${orderMasterDtoData.getBuyerOrderId()}" type="button" class="btn btn-default btn-warning">完结订单</a>
                            <a href="/sell/seller/cancelOrder?orderId=${orderMasterDtoData.getBuyerOrderId()}" type="button" class="btn btn-default btn-danger">取消订单</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>