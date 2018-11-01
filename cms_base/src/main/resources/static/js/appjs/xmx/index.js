
$(function(){
    vm.init();
    vm.listContent();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        searchParam:{searchKey:'',contCategoryId:''},
        contactInfo:{},
        productList:[],
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
        ],
        limit:100,
        offset:0,
        langType:'en',
        categoryName:'',
        events:[],
        contentList: []
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
                    var contCategoryId = data.children[0].id;
                    var categoryName = data.children[0].text;
                    vm.events=data.state.events;
                    vm.contactInfo=data.state.contactInfo;
                    //初始化产品
                    vm.listProduct(contCategoryId,categoryName);
                }
            });

        } ,
        listProduct: function (contCategoryId,categoryName) {
            vm.categoryName=categoryName;
            $.ajax({
                url: "/contXmx/listProductByCategory",
                type: "post",
                data: {
                    limit:vm.limit,
                    offset:vm.offset,
                    contCategoryId:contCategoryId,
                    langType:vm.langType
                },
                success: function (data) {
                    vm.productList=data.rows;
                }
            });
        },
        searchProduct:function () {
            window.location.href="/contXmx/viewSearch?contCategoryId="+vm.searchParam.contCategoryId+"&searchKey="+vm.searchParam.searchKey;
        },
        viewProduct:function (contProductId) {
            window.location.href="/contXmx/showProduct/"+contProductId;
        },
        listContent: function () {
            $.ajax({
                url: "/contXmx/listContent",
                type: "post",
                data: {
                    limit: 10,
                    offset: 0,
                    type: 'aboutUs',
                    langType: vm.langType
                },
                success: function (data) {
                    vm.contentList = data.rows;
                }
            });
        },
        submit:function () {

            if(!vm.checkadd()){
                return ;
            }

            vm.formDO.title="您有新消息了~";

            $.ajax({
                url: "/contTpc/saveInquery",
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