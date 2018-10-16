
$(function(){
    vm.init();
    vm.getProduct();
    vm.listHotProduct();
    vm.getProductDetailNavBar();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        contactInfo:{},
        productList:{},
        hotProductList:{},
        langType:'english',
        events:[],
        activeIndex:0,
        activeProduct:{},
        navBar:{},
        imgActiveIndex:0
    },
    methods: {
        init: function () {

            $.ajax({
                url: "/cont/contCategory/treeInfo",
                type: "post",
                data: {
                    langType: vm.langType
                },
                success: function (data) {
                    vm.categoryTree=data;
                    vm.events=data.state.events;
                    vm.contactInfo=data.state.contactInfo;
                }
            });

        } ,
        listProduct: function (contCategoryId,categoryName) {

            //跳转到详情产品页
            window.location.href="/contXmx/openViewListProduct/"+contCategoryId+"/"+categoryName;

        },
        getProduct: function () {
            $.ajax({
                url: "/contXmx/listCateProdsByProdId",
                type: "post",
                data: {
                    langType:vm.langType
                },
                success: function (data) {
                    vm.productList=data.rows;

                    for (var i=0;i<data.rows.length;i++)
                    {
                        if(data.rows[i].contProductId==data.contProductId){
                            vm.activeIndex=i;
                            vm.activeProduct=data.rows[i];
                            break;
                        }
                    }
                }
            });
        },
        setActiveIndex:function (activeIndex) {
            vm.activeIndex=activeIndex;
        },
        caculate:function (activeIndex,value) {
            var result=activeIndex+value;
            if(result<0){
                return 0;
            }
            if(result>=vm.productList.length){
                return vm.productList.length-1;
            }

            return activeIndex+value;
        },
        listHotProduct:function () {
            $.ajax({
                url: "/contXmx/listRecProduct",
                type: "post",
                data: {
                    langType: vm.langType,
                    level:11
                },
                success: function (data) {
                    vm.hotProductList=data.rows;
                }
            });
        },
        changeCateProduct:function (contProductId) {
            $.ajax({
                url: "/contXmx/listCateProdsByProdId",
                type: "post",
                data: {
                    langType:vm.langType,
                    contProductId:contProductId
                },
                success: function (data) {
                    vm.productList=data.rows;

                    for (var i=0;i<data.rows.length;i++)
                    {
                        if(data.rows[i].contProductId==data.contProductId){
                            vm.activeIndex=i;
                            vm.activeProduct=data.rows[i];
                            break;
                        }
                    }
                }
            });
        },
        getProductDetailNavBar:function () {
            $.ajax({
                url: "/contXmx/listProductDetailNavBar",
                type: "post",
                data: {
                    langType: vm.langType
                },
                success: function (data) {
                    for(var i=0;i<data.rows.length;i++){
                        if(data.rows[i].parentCategoryId==0){
                            vm.navBar.first=data.rows[i].contCategoryInfoDO;
                        }else{
                            vm.navBar.second=data.rows[i].contCategoryInfoDO;
                        }
                    }
                    vm.$forceUpdate();
                }
            });
        },
        imgActive:function (imgIndex) {
            vm.imgActiveIndex=imgIndex;
        }



    }
});