$(
    function () {

        let shopId = getQueryString("shopId");
        let isEdit = !!shopId;
        let initUrl = "/shop_admin/shop_init_info";
        let registerShopUrl = "/shop_admin/registration_shop"
        let shopInfoUrl = "/shop_admin/shop?shopId=" + shopId;
        let editShopUrl = "/shop_admin/modification_shop"
        if (!isEdit) {
            getShopInitInfo();
        } else {
            getShopInfo(shopId);
        }


        function getShopInitInfo() {

            $.getJSON(initUrl, function (data) {
                let areaList = data.areaList;
                let shopCategoryList = data.shopCategoryList;
                $.each(areaList, function (key, item) {
                    $("#area").append("<option value='" + item.areaId + "'>" + item.areaName + "</option>")
                })
                $.each(shopCategoryList, function (key, item) {
                    $("#shop-category").append("<option value='" + item.shopCategoryId + "'>" + item.shopCategoryName + "</option>")
                })
            });

        }

        function getShopInfo(shopId) {
            $.getJSON(shopInfoUrl, function(data) {
                if (data.success) {
                    // 若访问成功，则依据后台传递过来的店铺信息为表单元素赋值
                    let shop = data.shop;
                    $('#shop-name').val(shop.shopName);
                    $('#shop-addr').val(shop.shopAddr);
                    $('#shop-phone').val(shop.phone);
                    $('#shop-desc').val(shop.shopDesc);
                    // 给店铺类别选定原先的店铺类别值
                    var shopCategory = '<option value="'
                        + shop.shopCategory.shopCategoryId + '" selected>'
                        + shop.shopCategory.shopCategoryName + '</option>';
                    var tempAreaHtml = '';
                    // 初始化区域列表
                    data.areaList.map(function(item, index) {
                        tempAreaHtml += '<option value="' + item.areaId + '">'
                            + item.areaName + '</option>';
                    });
                    $('#shop-category').html(shopCategory);
                    // 不允许选择店铺类别
                    $('#shop-category').attr('disabled', 'disabled');
                    $('#area').html(tempAreaHtml);
                    // 给店铺选定原先的所属的区域
                    $("#area option[data-id='" + shop.area.areaId + "']").attr(
                        "selected", "selected");
                }
            });
        }

        $('#submit').click(function () {
            // 创建shop对象
            let shop = {};

            if (isEdit){
               shop.shopId = shopId;
            }

            // 获取表单里的数据并填充进对应的店铺属性中
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();

            // 选择选定好的店铺类别
            shop.shopCategory = {
                shopCategoryId: $('#shop-category').find("option:selected").val()
            };
            // 选择选定好的区域信息
            shop.area = {
                areaId: $('#area').find("option:selected").val()
            };
            // 获取上传的图片文件流
            var shopImg = $('#shop-img')[0].files[0];
            // 生成表单对象，用于接收参数并传递给后台
            var formData = new FormData();
            // 添加图片流进表单对象里
            formData.append('shopImg', shopImg);
            // 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
            formData.append('shopStr', JSON.stringify(shop));
            // 获取表单里输入的验证码
            let verifyCodeActual = $("#verifyCode").val();
            if (!verifyCodeActual) {
                alert('请输入验证码！');
                return;
            }
            formData.append("verifyCodeActual", verifyCodeActual);
            // 将数据提交至后台处理相关操作
            $.ajax({
                url: isEdit? editShopUrl : registerShopUrl,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        alert("提交成功!");
                        window.location.href = "/shop_admin/shop_list";

                    } else {
                        alert("提交失败 "+data.errMsg)
                    }
                    // 点击验证码图片的时候，注册码会改变
                    $('#captcha_img').click();
                }
            });
        });
    }
)