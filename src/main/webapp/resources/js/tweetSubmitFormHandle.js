/* not needed for the momment
 * 
 $("#add-tweet-form input[type='submit']").click(function(e){
  e.preventDefault();
  var serializedData =$("#add-tweet-form").serializeObject();
  $.ajax({
   contentType: 'application/json',
   type:"POST",
   data:JSON.stringify(serializedData),
   success : function(data, textStatus, jqXHR) {
		tableBodyPoint = $('#tweet-table > tbody');
		tableBody = false;
		// construct the table row
		tr = '';
		tr += '<tr>';
		tr += '<td>';
		tr += data['id'];
		tr += '</td>';
		tr += '<td>';
		tr += data['tweet'];
		tr += '</td>';
		tr += '<td>';
			var date = new Date(data['date']);
			var dateString = ''+date.getFullYear() + '-'+ date.getMonth() + '-' + date.getDate() + ' ' +date.getHours() + ':' +date.getMinutes() + ':' + date.getSeconds() ;
		tr += dateString;
		tr += '</td>';
		tr += '</tr>';
		
		// end contruct row
		if (tableBodyPoint.children().size() > 0) {
			firstBodyChild = $('#tweet-table > tbody').children()[0];
			$(firstBodyChild).before(tr);
		} else {
			tableBodyPoint.append(tr);			
		}
	},
	error : function(jqXHR, textStatus, errorThrown) {
		alert("There is an error with tweet publishing");
	}
  });
 });


*/