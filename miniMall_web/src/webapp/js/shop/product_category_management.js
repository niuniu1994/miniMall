$(function (message) {

    let pageNum = 1;

    var listUrl = '/shop_admin/product_category_list';
    var addUrl = '/shop_admin/add_product_categories';
    var deleteUrl = '/shop_admin/remove_product_category';
    getList();

    $("#previous").on("click",function (){
        if (pageNum > 1){
            pageNum--;
        }
        getList();
    })

    $("#next").on("click",function (){
        pageNum++;
        getList();
    })

    function getList() {
        $.ajax({
            url:listUrl,
            type: "get",
            data: {'pageNum': pageNum},
            dataType: "json",
            success:function (data){
                if (data.success) {
                    var dataList = data.productCategoryList.list;
                    $('.category-wrap').html('');
                    var tempHtml = '';
                    dataList
                        .map(function (item, index) {
                            tempHtml += ''
                                + '<div class="row row-product-category now">'
                                + '<div class="col-33 product-category-name">'
                                + item.productCategoryName
                                + '</div>'
                                + '<div class="col-33">'
                                + item.priority
                                + '</div>'
                                + '<div class="col-33"><a href="#" class="button delete" data-id="'
                                + item.productCategoryId
                                + '">删除</a></div>'
                                + '</div>';
                        });
                    $('.category-wrap').append(tempHtml);
                }
            }
        })


    }

    $('#new')
        .click(
            function () {
                var tempHtml = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                    + '</div>';
                $('.category-wrap').append(tempHtml);
            });
    $('#submit').click(function () {
        var tempArr = $('.temp');
        var productCategoryList = [];
        tempArr.map(function (index, item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: 'POST',
            data: JSON.stringify(productCategoryList),
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    alert('提交成功！');
                    getList();
                } else {
                    alert('提交失败！');
                }
            }
        });
    });

    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();

        });
    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;

            $.confirm('确定么?', function () {
                $.ajax({
                    url: deleteUrl,
                    type: 'POST',
                    data: {
                        productCategoryId: target.dataset.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            alert('删除成功！');
                            getList();
                        } else {
                            alert('删除失败！');
                        }
                    }
                });
            });
        });
});