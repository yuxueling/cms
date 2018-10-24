
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
        searchParam:{searchKey:'',contCategoryId:''},
        contactInfo:{},
        productList:{},
        hotProductList:{},
        langType:'en',
        events:[],
        activeIndex:0,
        activeProduct:{},
        navBar:{},
        imgActiveIndex:0,
        formDO: {},
        formDataDOList: [
            {title: 'Subject', value: ''},
            {title: 'Content', value: ''},
            {title: 'Name', value: ''},
            {title: 'E-mail', value: ''},
            {title: 'Country/Region', value: ''},
            {title: 'Company', value: ''},
            {title: 'Tel', value: ''},
            {title: 'Address', value: ''}
        ]
    },
    methods: {
        init: function () {

            $.ajax({
                url: "/cont/contXmx/treeInfo",
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
        searchProduct:function () {
            window.location.href="/contXmx/viewSearch?contCategoryId="+vm.searchParam.contCategoryId+"&searchKey="+vm.searchParam.searchKey;
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
        },
        submit:function () {

            if(!vm.checkadd()){
                return ;
            }

            vm.formDO.title="您有新消息了~";

            $.ajax({
                url: "/cont/contForm/openSave",
                type: "post",
                data: {
                    contForm: JSON.stringify(vm.formDO),
                    contFormData:JSON.stringify(vm.formDataDOList)
                },
                success: function (data) {
                    if(data.code==0){
                        //刷新页面
                        window.location.reload();
                    }
                }
            });
        },
        checkadd:function () {
            if (document.feedback.name.value == '') {
                alert('Please put the name!');
                document.feedback.name.focus
                return false;
            }
            if (document.feedback.email.value == '') {
                alert('Please put the email address!');
                document.feedback.email.focus
                return false;
            }
            var Mail = document.feedback.email.value;
            if (Mail.indexOf('@', 0) == -1 || Mail.indexOf('.', 0) == -1) {
                alert('Please put the correct e-mail address！');
                document.feedback.email.focus();
                return false;
            }
            if (document.feedback.content.value == '') {
                alert('Please put the detailed information!');
                document.feedback.content.focus
                return false;
            }
            if (document.feedback.tel.value == '') {
                alert('Please enter the verification code!');
                document.feedback.code.focus
                return false;
            }
            return true;
        }




    }
});