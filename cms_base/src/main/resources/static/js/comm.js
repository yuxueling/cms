// JScript 
function doc(obj){
    return document.getElementById(obj);
}
/*-------------------------- end ---------------------------------*/

function CheckAll(form)
  {
  for (var i=0;i<form.elements.length;i++)
    {
    var e = form.elements[i];
    if (e.Name != "chkAll")
       e.checked = form.chkAll.checked;
    }
  }


function search_(){
	var	Getkey = doc("modval_").value;
	
	if (Getkey!=''){
			window.location = "search.asp?key=" + Getkey;
		}
	else{
		doc("modval_").focus();
	}	
}

function searchwithtype_(){
	var	Getkey = doc("modval_").value;
	var Gettype= doc("type_").value;
	
	if (Getkey!=''){
		window.location ="search.asp?big_id=" + Gettype + "&key="+Getkey;
		}
	else{
		doc("modval_").focus();
	}	
}

function searchsimple_(){
	var	Getkey = doc("modval_").value;
	
	if (Getkey!='' &&Gettype!=''){
			window.location = "search.asp?key=" + Getkey;
	}
	else{
		doc("modval_").focus();
	}	
}


/*------------------------ Add to Favorite ------------------------*/
function favPage()
{
    var pageName=window.location.href;
    var nameArr =pageName.split("?");
    pageName=nameArr[0] + "?" + nameArr[1];
    if (window.sidebar)
    {
        window.sidebar.addPanel(document.title,pageName,"");
    }
    else   if(document.all )
    { 
        window.external.AddFavorite(pageName,document.title);
    }
    else
    {
        return true;
    }
} 
