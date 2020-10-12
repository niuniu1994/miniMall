$(function() {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/shop_admin/shop_management_info?shopId=' + shopId;
    $.getJSON(shopInfoUrl, function(data) {
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
            $('#shopInfo')
                .attr('href', '/shop_admin/shop_operation?shopId=' + shopId);
        }
    });
});