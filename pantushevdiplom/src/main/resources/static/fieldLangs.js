function reloadTableList() {
    let fieldName = $('#input-fieldName').val();
    loadTablesList( fieldName);
}

function loadTablesList(fieldName) {
    let v_url = '/api/field/' + fieldName + '/language';
    $.get(v_url).done(function (entities) {
        $('#tables-list > tbody').empty();

        entities.forEach(function (enitity) {
            $('#tables-list > tbody').append(`
                    <tr>
                        <td>${enitity.idLanguageCd}</td>
                        <td>${enitity.labelLong}</td>
                        <td>
                            <a href="/table/edit/${enitity.id}">Редактировать</a>
                        </td>
                        <td>
                            <a href="#" onclick="deleteTable(${enitity.id})">Удалить</a>
                        </td>
                    </tr>
                `)
        });
    })
}





