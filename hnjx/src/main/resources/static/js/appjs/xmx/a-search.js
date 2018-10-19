$(function () {
    vm.init();
    vm.searchProduct();
});


var vm = new Vue({
    el: '#site',
    data: {
        categoryTree: {},
        contactInfo:{},
        langType: 'english',
        events: [],
        searchParam:{searchKey:'',contCategoryId:''},
        productList:[],
        pageTool:{
            limit:12,
            offset:0,
            currentPage:1,
            totalPages:0,
            pageList:[]
        },
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
                url: "/cont/contCategory/treeInfo",
                type: "post",
                data: {
                    langType: vm.langType
                },
                success: function (data) {
                    vm.categoryTree = data;
                    vm.events = data.state.events;
                    vm.contactInfo = data.state.contactInfo;
                }
            });
        },
        searchProduct: function () {
            $.ajax({
                url: "/contXmx/searchProductByCategory",
                type: "post",
                data: {
                    limit:vm.pageTool.limit,
                    offset:vm.pageTool.offset,
                    contCategoryId:vm.searchParam.contCategoryId,
                    searchKey:vm.searchParam.searchKey,
                    langType:vm.langType
                },
                success: function (data) {
                    vm.productList=data.rows;
                    vm.pageTool.totalPages=Math.ceil(data.total/vm.pageTool.limit);
                    vm.pageTool.pageList.length = 0;
                    for(var i=1;i<=vm.pageTool.totalPages;i++){
                        vm.pageTool.pageList.push(i);
                    }
                }
            });
        },
        selectPage:function (page,step) {
           if(page=='currentPage'){
               page=vm.pageTool.currentPage;
           }
           if(page=='endPage'){
               page=vm.pageTool.totalPages;
           }

            var currentPage=page+step;
            if(page<=1 && step==-1){
                currentPage=1;
            }
            if(page>=vm.pageTool.totalPages && step==1){
                currentPage=vm.pageTool.totalPages;
            }
            vm.pageTool.currentPage = currentPage;
            vm.pageTool.offset = (currentPage-1) * vm.pageTool.limit;
            vm.listProduct();
        },
        viewProduct:function (contProductId) {
            window.location.href="/contXmx/showProduct/"+contProductId;
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