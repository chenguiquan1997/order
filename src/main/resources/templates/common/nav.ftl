<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li>
            <a href="/sell/seller/findOrderListBySeller"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">
            <a href="/sell/seller/product/searchAllProductBySeller" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">商品操作</li>
                <li><a href="/sell/seller/product/searchAllProductBySeller">商品列表</a></li>
                <li><a href="/sell/seller/product/productUpdateOrAddUI">添加商品</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">类目操作</li>
                <li><a href="/sell/seller/category/searchCategoryList">类目列表</a></li>
                <li><a href="/sell/seller/category/categoryUpdateOrAddUI">添加类目</a></li>
            </ul>
        </li>

        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>