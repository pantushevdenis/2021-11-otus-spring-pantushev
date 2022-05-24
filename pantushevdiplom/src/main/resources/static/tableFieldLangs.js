function reloadTableList() {
    let tableName = $('#input-tableName').val();
    let fieldName = $('#input-fieldName').val();
    loadTablesList( tableName, fieldName);
}

function loadTablesList(tableName, fieldName) {
    let v_url = '/api/table/' + tableName + '/field/' + fieldName + '/language';
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





