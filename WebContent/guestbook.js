/**
 * receive all guest book entries from backend
 */
function guestbookCallBack(data){
	var html="";
	var nr = data.length
    //check the list for each json object
    	$.each(data.reverse(), function(i, json){
    		if(json.success == true){
    			//build html for guestbook entry list
        		html += "<div class='inner'><h3>#" + nr + " " + json.author + ", " + json.date + "</h3><span>"+json.comment+"<span></div><br>";
        		nr--;
        	}
    		else{
    			html = "<div class='inner'><h3>"+ json.message + "</h3></div>";
    		}
    		
    	});
	//assign html to entries div
	$('#entries').append(html);
}
/**
 * 
 */
 function getAllGuestBookEntries(){
	 $.getJSON("http://localhost:8080/ZerosOnesGuestBook/guestbook?callback=?");
} 

/**
*save entry functions,  get values from input
*/
function saveEntry(){
	
	var author = ""; 
	var comment = "";
	
	//get input values
	author = $('#author').val(); 
	comment = $('#comment').val();
	
	//check if fields are filled otherwise errormessage
	if(author != "" && comment != ""){
		saveEntryRest(author, comment);
	}
	else{
		if (author == "" && comment == ""){
			$('#author').css({'background-color' : '#F08080'});
			$('#comment').css({'background-color' : '#F08080'});
			$('#errormessage').html("  Fill author and comment field please");
			
		} 
		else if(author == "" ){
			$('#author').css({'background-color' : '#F08080'});
			$('#comment').css({'background-color' : '#FFFFFF'});
			$('#errormessage').html("  Fill author field please");
		}
		else{

			$('#author').css({'background-color' : '#FFFFFF' });
			$('#comment').css({'background-color' : '#F08080'});
			$('#errormessage').html("  Fill comment field please");
		}
	}
	 
}
/**
 * communitcation to rest 
 * @param author input author 
 * @param comment input comment
 */
function saveEntryRest(author, comment){
	 $.getJSON("http://localhost:8080/ZerosOnesGuestBook/guestbook/new/"+encodeURIComponent(author)+"/"+encodeURIComponent(comment)+"?callback=?");

}
/**
 * callback for new entry
 * @param json json data
 */
function newEntryCallBack(json){
	$('#errormessage').css({'color' : '#90EE90'});
	$('#errormessage').html(json.message);
	window.location.href = "../WebContent/Guestbook.html";
}