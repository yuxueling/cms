
$(function(){
    vm.init();
    vm.listNews();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        searchParam:{searchKey:'',contCategoryId:''},
        contactInfo:{},
        langType:'en',
        events:[],
        newsList:[],
        pageTool:{
            limit:12,
            offset:0,
            currentPage:1,
            currentLength:0,
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

        },
        listProduct: function (contCategoryId,categoryName) {
            //跳转到详情产品页
            window.location.href="/contXmx/openViewListProduct/"+contCategoryId+"/"+categoryName;
        },
        searchProduct:function () {
            window.location.href="/contXmx/viewSearch?contCategoryId="+vm.searchParam.contCategoryId+"&searchKey="+vm.searchParam.searchKey;
        },
        listNews: function () {
            $.ajax({
                url: "/contXmx/listContent",
                type: "post",
                data: {
                    limit:vm.pageTool.limit,
                    offset:vm.pageTool.offset,
                    type:'news',
                    langType:vm.langType
                },
                success: function (data) {
                    vm.newsList=data.rows;
                    vm.pageTool.currentLength=data.rows.length;
                    vm.pageTool.totalPages=Math.ceil(data.total/vm.pageTool.limit);
                    vm.pageTool.pageList.length = 0;
                    for(var i=1;i<=vm.pageTool.totalPages;i++){
                        vm.pageTool.pageList.push(i);
                    }
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