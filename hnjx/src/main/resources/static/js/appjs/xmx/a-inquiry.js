$(function () {
    vm.init();
});


var vm = new Vue({
    el: '#site',
    data: {
        categoryTree: {},
        contactInfo:{},
        langType: 'english',
        events: [],
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
        countryArr: [
            {name: 'Afghanistan', value: 'Afghanistan'},
            {name: 'Albania', value: 'Albania'},
            {name: 'Algeria', value: 'Algeria'},
            {name: 'American Samoa', value: 'American Samoa'},
            {name: 'Andorra', value: 'Andorra'},
            {name: 'Angola', value: 'Angola'},
            {name: 'Anguilla', value: 'Anguilla'},
            {name: 'Antarctica', value: 'Antarctica'},
            {name: 'Antigua and Barbuda', value: 'Antigua and Barbuda'},
            {name: 'Argentina', value: 'Argentina'},
            {name: 'Armenia', value: 'Armenia'},
            {name: 'Aruba', value: 'Aruba'},
            {name: 'Australia', value: 'Australia'},
            {name: 'Austria', value: 'Austria'},
            {name: 'Azerbaijan', value: 'Azerbaijan'},
            {name: 'Bahamas', value: 'Bahamas'},
            {name: 'Bahrain', value: 'Bahrain'},
            {name: 'Bangladesh', value: 'Bangladesh'},
            {name: 'Barbados', value: 'Barbados'},
            {name: 'Belarus', value: 'Belarus'},
            {name: 'Belgium', value: 'Belgium'},
            {name: 'Belize', value: 'Belize'},
            {name: 'Benin', value: 'Benin'},
            {name: 'Bermuda', value: 'Bermuda'},
            {name: 'Bhutan', value: 'Bhutan'},
            {name: 'Bolivia', value: 'Bolivia'},
            {name: 'Bosnia and Herzegovina', value: 'Bosnia and Herzegovina'},
            {name: 'Botswana', value: 'Botswana'},
            {name: 'Bouvet Island', value: 'Bouvet Island'},
            {name: 'Brazil', value: 'Brazil'},
            {name: 'British Indian Ocean Territory', value: 'British Indian Ocean Territory'},
            {name: 'Brunei Darussalam', value: 'Brunei Darussalam'},
            {name: 'Bulgaria', value: 'Bulgaria'},
            {name: 'Burkina Faso', value: 'Burkina Faso'},
            {name: 'Burundi', value: 'Burundi'},
            {name: 'Cambodia', value: 'Cambodia'},
            {name: 'Cameroon', value: 'Cameroon'},
            {name: 'Canada', value: 'Canada'},
            {name: 'Cape Verde', value: 'Cape Verde'},
            {name: 'Cayman Islands', value: 'Cayman Islands'},
            {name: 'Central African Republic', value: 'Central African Republic'},
            {name: 'Chad', value: 'Chad'},
            {name: 'Chile', value: 'Chile'},
            {name: 'China', value: 'China'},
            {name: 'Christmas Island', value: 'Christmas Island'},
            {name: 'Cocos (Keeling)   Islands', value: 'Cocos (Keeling) Islands'},
            {name: 'Colombia', value: 'Colombia'},
            {name: 'Comoros', value: 'Comoros'},
            {name: 'Congo', value: 'Congo'},
            {name: 'Congo, The Democratic Republic Of The', value: 'Congo, The Democratic Republic Of The'},
            {name: 'Cook Islands', value: 'Cook Islands'},
            {name: 'Costa Rica', value: 'Costa Rica'},
            {name: 'Cote D Ivoire', value: 'Cote D Ivoire'},
            {name: 'Croatia (local name: Hrvatska)', value: 'Croatia (local name:Hrvatska)'},
            {name: 'Cuba', value: 'Cuba'},
            {name: 'Cyprus', value: 'Cyprus'},
            {name: 'Czech Republic', value: 'Czech Republic'},
            {name: 'Denmark', value: 'Denmark'},
            {name: 'Djibouti', value: 'Djibouti'},
            {name: 'Dominica', value: 'Dominica'},
            {name: 'Dominican Republic', value: 'Dominican Republic'},
            {name: 'East Timor', value: 'East Timor'},
            {name: 'Ecuador', value: 'Ecuador'},
            {name: 'Egypt', value: 'Egypt'},
            {name: 'El Salvador', value: 'El Salvador'},
            {name: 'Equatorial Guinea', value: 'Equatorial Guinea'},
            {name: 'Eritrea', value: 'Eritrea'},
            {name: 'Estonia', value: 'Estonia'},
            {name: 'Ethiopia', value: 'Ethiopia'},
            {name: 'Falkland Islands (Malvinas)', value: 'Falkland Islands(Malvinas)'},
            {name: 'Faroe Islands', value: 'Faroe Islands'},
            {name: 'Fiji', value: 'Fiji'},
            {name: 'Finland', value: 'Finland'},
            {name: 'France', value: 'France'},
            {name: 'France Metropolitan', value: 'France Metropolitan'},
            {name: 'French Guiana', value: 'French Guiana'},
            {name: 'French Polynesia', value: 'French Polynesia'},
            {name: 'French Southern   Territories', value: 'French Southern Territories'},
            {name: 'Gabon', value: 'Gabon'},
            {name: 'Gambia', value: 'Gambia'},
            {name: 'Georgia', value: 'Georgia'},
            {name: 'Germany', value: 'Germany'},
            {name: 'Ghana', value: 'Ghana'},
            {name: 'Gibraltar', value: 'Gibraltar'},
            {name: 'Greece', value: 'Greece'},
            {name: 'Greenland', value: 'Greenland'},
            {name: 'Grenada', value: 'Grenada'},
            {name: 'Guadeloupe', value: 'Guadeloupe'},
            {name: 'Guam', value: 'Guam'},
            {name: 'Guatemala', value: 'Guatemala'},
            {name: 'Guinea', value: 'Guinea'},
            {name: 'Guinea-Bissau', value: 'Guinea-Bissau'},
            {name: 'Guyana', value: 'Guyana'},
            {name: 'Haiti', value: 'Haiti'},
            {name: 'Heard and Mc Donald Islands', value: 'Heard and Mc Donald Islands'},
            {name: 'Honduras', value: 'Honduras'},
            {name: 'Hong Kong', value: 'Hong Kong'},
            {name: 'Hungary', value: 'Hungary'},
            {name: 'Iceland', value: 'Iceland'},
            {name: 'India', value: 'India'},
            {name: 'Indonesia', value: 'Indonesia'},
            {name: 'Iran (Islamic Republic of)', value: 'Iran (Islamic Republic of)'},
            {name: 'Iraq', value: 'Iraq'},
            {name: 'Ireland', value: 'Ireland'},
            {name: 'Israel', value: 'Israel'},
            {name: 'Italy', value: 'Italy'},
            {name: 'Jamaica', value: 'Jamaica'},
            {name: 'Japan', value: 'Japan'},
            {name: 'Jordan', value: 'Jordan'},
            {name: 'Kazakhstan', value: 'Kazakhstan'},
            {name: 'Kenya', value: 'Kenya'},
            {name: 'Kiribati', value: 'Kiribati'},
            {name: 'Kuwait', value: 'Kuwait'},
            {name: 'Kyrgyzstan', value: 'Kyrgyzstan'},
            {name: "Lao People's Democratic   Republic", value: "Lao People's Democratic Republic"},
            {name: 'Latvia', value: 'Latvia'},
            {name: 'Lebanon', value: 'Lebanon'},
            {name: 'Lesotho', value: 'Lesotho'},
            {name: 'Liberia', value: 'Liberia'},
            {name: 'Libyan Arab Jamahiriya', value: 'Libyan Arab Jamahiriya'},
            {name: 'Liechtenstein', value: 'Liechtenstein'},
            {name: 'Lithuania', value: 'Lithuania'},
            {name: 'Luxembourg', value: 'Luxembourg'},
            {name: 'Macau', value: 'Macau'},
            {name: 'Macedonia', value: 'Macedonia'},
            {name: 'Madagascar', value: 'Madagascar'},
            {name: 'Malawi', value: 'Malawi'},
            {name: 'Malaysia', value: 'Malaysia'},
            {name: 'Maldives', value: 'Maldives'},
            {name: 'Mali', value: 'Mali'},
            {name: 'Malta', value: 'Malta'},
            {name: 'Marshall Islands', value: 'Marshall Islands'},
            {name: 'Martinique', value: 'Martinique'},
            {name: 'Mauritania', value: 'Mauritania'},
            {name: 'Mauritius', value: 'Mauritius'},
            {name: 'Mayotte', value: 'Mayotte'},
            {name: 'Mexico', value: 'Mexico'},
            {name: 'Micronesia', value: 'Micronesia'},
            {name: 'Moldova', value: 'Moldova'},
            {name: 'Monaco', value: 'Monaco'},
            {name: 'Mongolia', value: 'Mongolia'},
            {name: 'Montserrat', value: 'Montserrat'},
            {name: 'Montenegro', value: 'Montenegro'},
            {name: 'Morocco', value: 'Morocco'},
            {name: 'Mozambique', value: 'Mozambique'},
            {name: 'Myanmar', value: 'Myanmar'},
            {name: 'Namibia', value: 'Namibia'},
            {name: 'Nauru', value: 'Nauru'},
            {name: 'Nepal', value: 'Nepal'},
            {name: 'Netherlands', value: 'Netherlands'},
            {name: 'Netherlands Antilles', value: 'Netherlands Antilles'},
            {name: 'New Caledonia', value: 'New Caledonia'},
            {name: 'New Zealand', value: 'New Zealand'},
            {name: 'Nicaragua', value: 'Nicaragua'},
            {name: 'Niger', value: 'Niger'},
            {name: 'Nigeria', value: 'Nigeria'},
            {name: 'Niue', value: 'Niue'},
            {name: 'Norfolk Island', value: 'Norfolk Island'},
            {name: 'North Korea', value: 'North Korea'},
            {name: 'Northern Mariana Islands', value: 'Northern Mariana Islands'},
            {name: 'Norway', value: 'Norway'},
            {name: 'Oman', value: 'Oman'},
            {name: 'Pakistan', value: 'Pakistan'},
            {name: 'Palau', value: 'Palau'},
            {name: 'Palestine', value: 'Palestine'},
            {name: 'Panama', value: 'Panama'},
            {name: 'Papua New Guinea', value: 'Papua New Guinea'},
            {name: 'Paraguay', value: 'Paraguay'},
            {name: 'Peru', value: 'Peru'},
            {name: 'Philippines', value: 'Philippines'},
            {name: 'Pitcairn', value: 'Pitcairn'},
            {name: 'Poland', value: 'Poland'},
            {name: 'Portugal', value: 'Portugal'},
            {name: 'Puerto Rico', value: 'Puerto Rico'},
            {name: 'Qatar', value: 'Qatar'},
            {name: 'Reunion', value: 'Reunion'},
            {name: 'Romania', value: 'Romania'},
            {name: 'Russian Federation', value: 'Russian Federation'},
            {name: 'Rwanda', value: 'Rwanda'},
            {name: 'Saint Kitts and Nevis', value: 'Saint Kitts and Nevis'},
            {name: 'Saint Lucia', value: 'Saint Lucia'},
            {name: 'Saint Vincent and the   Grenadines', value: 'Saint Vincent and the Grenadines'},
            {name: 'Samoa', value: 'Samoa'},
            {name: 'San   Marino', value: 'San Marino'},
            {name: 'Sao Tome and Principe', value: 'Sao Tome and Principe'},
            {name: 'Saudi Arabia', value: 'Saudi Arabia'},
            {name: 'Serbia', value: 'Serbia'},
            {name: 'Senegal', value: 'Senegal'},
            {name: 'Seychelles', value: 'Seychelles'},
            {name: 'Sierra Leone', value: 'Sierra Leone'},
            {name: 'Singapore', value: 'Singapore'},
            {name: 'Slovakia (Slovak Republic)', value: 'Slovakia (Slovak Republic)'},
            {name: 'Slovenia', value: 'Slovenia'},
            {name: 'Solomon Islands', value: 'Solomon Islands'},
            {name: 'Somalia', value: 'Somalia'},
            {name: 'South Africa', value: 'South Africa'},
            {name: 'South Korea', value: 'South Korea'},
            {name: 'Spain', value: 'Spain'},
            {name: 'Sri Lanka', value: 'Sri Lanka'},
            {name: 'St. Helena', value: 'St. Helena'},
            {name: 'St. Pierre and Miquelon', value: 'St. Pierre and Miquelon'},
            {name: 'Sudan', value: 'Sudan'},
            {name: 'Suriname', value: 'Suriname'},
            {name: 'Svalbard and Jan Mayen Islands', value: 'Svalbard and Jan Mayen Islands'},
            {name: 'Swaziland', value: 'Swaziland'},
            {name: 'Sweden', value: 'Sweden'},
            {name: 'Switzerland', value: 'Switzerland'},
            {name: 'Syrian Arab Republic', value: 'Syrian Arab Republic'},
            {name: 'Taiwan', value: 'Taiwan'},
            {name: 'Tajikistan', value: 'Tajikistan'},
            {name: 'Tanzania', value: 'Tanzania'},
            {name: 'Thailand', value: 'Thailand'},
            {name: 'Togo', value: 'Togo'},
            {name: 'Tokelau', value: 'Tokelau'},
            {name: 'Tonga', value: 'Tonga'},
            {name: 'Trinidad and Tobago', value: 'Trinidad and Tobago'},
            {name: 'Tunisia', value: 'Tunisia'},
            {name: 'Turkey', value: 'Turkey'},
            {name: 'Turkmenistan', value: 'Turkmenistan'},
            {name: 'Turks and Caicos   Islands', value: 'Turks and Caicos Islands'},
            {name: 'Tuvalu', value: 'Tuvalu'},
            {name: 'Uganda', value: 'Uganda'},
            {name: 'Ukraine', value: 'Ukraine'},
            {name: 'United Arab Emirates', value: 'United Arab Emirates'},
            {name: 'United Kingdom', value: 'United Kingdom'},
            {name: 'United States', value: 'United States'},
            {name: 'United States Minor Outlying Islands', value: 'United States Minor Outlying Islands'},
            {name: 'Uruguay', value: 'Uruguay'},
            {name: 'Uzbekistan', value: 'Uzbekistan'},
            {name: 'Vanuatu', value: 'Vanuatu'},
            {name: 'Vatican City State (Holy See)', value: 'Vatican City State (Holy See)'},
            {name: 'Venezuela', value: 'Venezuela'},
            {name: 'Vietnam', value: 'Vietnam'},
            {name: 'Virgin Islands (British)', value: 'Virgin Islands (British)'},
            {name: 'Virgin Islands   (U.S.)', value: 'Virgin Islands (U.S.)'},
            {name: 'Wallis And Futuna Islands', value: 'Wallis And Futuna Islands'},
            {name: 'Western Sahara', value: 'Western Sahara'},
            {name: 'Yemen', value: 'Yemen'},
            {name: 'Yugoslavia', value: 'Yugoslavia'},
            {name: 'Zambia', value: 'Zambia'},
            {name: 'Zimbabwe', value: 'Zimbabwe'},
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