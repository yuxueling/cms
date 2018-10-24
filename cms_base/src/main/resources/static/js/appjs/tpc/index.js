
$(function(){
    vm.listRecProduct();
    vm.getContent();
    vm.listCateTree();
});

var vm = new Vue({
    el: '#app',
    data: {
        categoryTree:{},
        searchParam:{searchKey:''},
        recProductList:[],
        aboutUsCont:{},
        formDO: {},
        formDataDOList: [
            {title: 'Feedback topic', value: ''},
            {title: 'Name', value: ''},
            {title: 'E-mail', value: ''},
            {title: 'Phone', value: ''},
            {title: 'Information description', value: ''}
        ],
        langType:'en',
        newsCont:[{gtmCreate:''}]
    },
    methods: {
        listCateTree:function () {
            $.ajax({
                url: "/contTpc/treeInfo",
                type: "post",
                data: {},
                success: function (data) {
                    vm.categoryTree=data;
                }
            });
        },
        listContent: function () {
            $.ajax({
                url: "/contTpc/listContent",
                type: "post",
                data: {
                    limit: 3,
                    offset: 0,
                    type: 'cmsNews',
                    langType: vm.langType
                },
                success: function (data) {
                    vm.newsCont = data.rows;
                }
            });
        },
        getContent:function () {
            $.ajax({
                url: "/contTpc/getContent",
                type: "post",
                data: {
                    type: "cmsAboutUs"
                },
                success: function (data) {
                    vm.aboutUsCont=data.aboutUsCont;
                }
            });
        },
        listRecProduct:function () {
            $.ajax({
                url: "/contTpc/listRecProduct",
                type: "post",
                data: {
                    langType: vm.langType,
                    level:11
                },
                success: function (data) {
                    vm.recProductList=data.rows;
                }
            });
        },
        viewProduct:function (contProductId) {
            window.location.href="/contTpc/showProduct/"+contProductId;
        },
        listProduct: function (contCategoryId, categoryName) {
            //跳转到产品页
            window.location.href = "/contTpc/openViewListProduct/" + contCategoryId + "/" + categoryName;
        },
        submit:function () {
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
                        alert("Success ！");
                        vm.clear();
                    }
                }
            });
        },
        clear:function () {
           var  formDataDOList= [
                {title: 'Feedback topic', value: ''},
                {title: 'Name', value: ''},
                {title: 'E-mail', value: ''},
                {title: 'Phone', value: ''},
                {title: 'Information description', value: ''}
            ];
           vm.formDataDOList=formDataDOList;
        },
        search:function () {
            var contCategoryId;
            var categoryName;

            if(vm.searchParam.searchKey || vm.searchParam.searchKey==''){
                contCategoryId='';
                categoryName='';
            }else {
                contCategoryId=categoryTree[vm.searchParam.searchKey].id;
                categoryName=categoryTree[vm.searchParam.searchKey].text;
            }
            vm.listProduct(contCategoryId,categoryName);
        },
        getD:function (timeStr) {
            return timeStr.substring(8,10);
        },
        getYM:function (timeStr) {
            return timeStr.substring(0,7);
        }


    }
});