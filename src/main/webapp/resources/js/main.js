function activateHeaderLink(linkId){
	$('#'+linkId).addClass("active");
}

function publishTweet(thisObject) {
	postData = $(thisObject).serializeArray();
	$.ajax({
		headers: {'Content-Type': 'application/json'},
		type : "POST",
		data : postData,
		success : function(data, textStatus, jqXHR) {
			tableBodyPoint = $('#tweet-table > tbody');
			tableBodyPoint.lastChild
			tableBody = false;
			// construct the table row
			tr = '';
			tr += '<tr>';
			tr += '<td>';
			tr += 'id';
			tr += '</td>';
			tr += '<td>';
			tr += '</td>';
			tr += '<td>';
			tr += data['date'];
			tr += '</td>';
			tr += '</tr>';
			// end contruct row
			if (tableBodyPoint.children().size() > 0) {
				alert('Consider when there are tweets to insert before');
			} else {
				tableBodyPoint.append(tr);
			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("There is an error with tweet publishing");
		}
	});

	return false;
}
