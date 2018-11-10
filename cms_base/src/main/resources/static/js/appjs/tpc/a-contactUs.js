//页头
function searchA() {
    var key = $("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function searchB() {
    var key = $("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function listProduct(contCategoryId) {
    vm.listProduct(contCategoryId);
}

$(function () {
    $("#m7").addClass("on");
    $("#m1").removeClass("on");
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        formDO: {},
        formDataDOList: [
            {title: 'Name *', value: ''},
            {title: 'Phone *', value: ''},
            {title: 'Email *', value: ''},
            {title: 'Message *', value: ''}
        ],
        errorList:[],
        success:"",
        clicking:false
    },
    methods: {
        submit:function () {
            if(vm.clicking){
                return;
            }else {
                vm.clicking=true;
            }

            if(!vm.validate()){
                return;
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
                        vm.clear();
                        vm.clearErrorList();
                        vm.success="Success ！";
                        vm.$forceUpdate();
                        setTimeout(function(){
                            vm.success="";
                            vm.$forceUpdate();
                        },5000);
                        vm.clicking=false;
                    }
                }
            });
        },
        clear:function () {
            var  formDataDOList= [
                {title: 'Name *', value: ''},
                {title: 'Phone *', value: ''},
                {title: 'Email *', value: ''},
                {title: 'Message *', value: ''}
            ];
            vm.formDataDOList=formDataDOList;
        },
        clearErrorList:function () {
            vm.errorList.length=0;
            vm.$forceUpdate();
        },
        validate:function () {
            vm.errorList.length=0;
            if(vm.formDataDOList[0].value){
                if(vm.formDataDOList[0].value.length>512){
                    vm.errorList.push("Name's length out of range !");
                }
            }else {
                vm.errorList.push("Name can not be empty !");
            }

            if(vm.formDataDOList[1].value){
                if(vm.formDataDOList[1].value.length>20){
                    vm.errorList.push("Phone's length out of range !");
                }
            }else {
                vm.errorList.push("Phone can not be empty !");
            }
            if(vm.formDataDOList[2].value){
                var re = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
                if (!re.test(vm.formDataDOList[2].value)) {
                    vm.errorList.push("Incorrect E-mail format !");
                }
            }else {
                vm.errorList.push("E-mail can not be empty !");
            }
            if(vm.formDataDOList[3].value){
                if(vm.formDataDOList[3].value.length>2048){
                    vm.errorList.push("Message's length out of range !");
                }
            }else {
                vm.errorList.push("Message can not be empty !");
            }

            if(vm.errorList.length>0){
                return false;
            }else{
                return true;
            }
        }
    }
});



