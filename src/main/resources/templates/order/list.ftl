<html>
    <head>
        <meta charset="utf-8">
        <title>卖家端商品列表</title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/sell/css/style.css">
    </head>

    <body>
        <div id="wrapper" class="toggled">
            <!--引入边栏-->
            <#include "../common/nav.ftl">
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>订单id</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付方式</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list orderMasterDtoPageData.getContent() as OrderMasterDto>
                                    <tr>
                                        <td>${OrderMasterDto.getBuyerOrderId()}</td>
                                        <td>${OrderMasterDto.buyerName}</td>
                                        <td>${OrderMasterDto.buyerPhone}</td>
                                        <td>${OrderMasterDto.buyerAddress}</td>
                                        <td>${OrderMasterDto.buyerOrderAmount}</td>
                                        <td>${OrderMasterDto.getOrderStatusByCode().message}</td>
                                        <td>微信</td>
                                        <td>${OrderMasterDto.getOrderPayStatusByCode().message}</td>
                                        <td>${OrderMasterDto.buyerOrderCreateTime}</td>
                                        <td>
                                            <a href="/sell/seller/searchOrderDetailsBySeller?orderId=${OrderMasterDto.getBuyerOrderId()}">详情</a>
                                        </td>
                                        <td>
                                            <#if OrderMasterDto.getOrderStatusByCode().message == "新订单">
                                                <a href="/sell/seller/cancelOrder?orderId=${OrderMasterDto.getBuyerOrderId()}">取消</a>
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>

                    <#--分页-->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                                <#if currentPageData lte 1>
                                    <li class="disabled"><a href="#">上一页</a></li>
                                <#else >
                                    <li><a href="/sell/seller/findOrderListBySeller?page=${currentPageData - 1}&size=${currentSizeData}">上一页</a></li>
                                </#if>
                                <#list 1..orderMasterDtoPageData.totalPages as index>
                                    <#if currentPageData == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/findOrderListBySeller?page=${index}&size=${currentSizeData}">${index}</a></li>
                                    </#if>
                                </#list>
                                <#if currentPageData gte orderMasterDtoPageData.totalPages>
                                    <li class="disabled"><a href="#">下一页</a></li>
                                <#else >
                                    <li><a href="/sell/seller/findOrderListBySeller?page=${currentPageData + 1}&size=${currentSizeData}">下一页</a></li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
