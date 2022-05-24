function reloadTableList() {
    let tableName = $('#input-tableName').val();
    loadTablesList( tableName);
}

function loadTablesList(tableName) {
    let v_url = '/api/table/' + tableName + '/language';
    $.get(v_url).done(function (entities) {
        $('#tables-list > tbody').empty();

        entities.forEach(function (entity) {
            $('#tables-list > tbody').append(`
                    <tr>
                        <td>${entity.languageCd}</td>
                        <td>${entity.descr}</td>
                        <td>
                            <a href="/tableLangs/edit?table_name=${entity.tableName}&language_cd=${entity.languageCd}">Редактировать</a>
                        </td>
                        <td>
                            <a href="#" onclick="deleteTable(${entity.tableName}, ${entity.languageCd})">Удалить</a>
                        </td>
                    </tr>
                `)
        });
    })
}

function loadObjectEdit() {
    //loadDropdownAuthors();
    //loadDropdownJenres();
    $('#tableName-input').attr('readonly', 'readonly');
    if ($('#isEdit-input').val() == 'true') {
        loadObjectData();
        $('#languageCd-input').attr('readonly', 'readonly');
    }
}

function loadObjectData() {
    let uri = '/api/table/' + $('#tableName-input').val() + '/language/' + $('#languageCd-input').val();
    $.get(uri).done(function (object) {
        $('#descr-input').val(object.descr);
    })
}


function saveObject() {
    let method = ($('#isEdit-input').val() == 'true' ? 'PUT' : 'POST');
    let uri = '/api/table/' + $('#tableName-input').val() + '/language/' + $('#languageCd-input').val();
    $.ajax(
        uri,
        {
            type: method,
            async: false,
            cache: false,
            data: JSON.stringify( {
                tableName: $('#tableName-input').val(),
                languageCd: $('#languageCd-input').val(),
                descr: $('#descr-input').val()
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (table) {
                window.location.href = '/tables';
            },
            error: function (jqXHR, textStatus, errorThrown) {
                let message = jqXHR.responseJSON.message;
                $('#errors').empty();
                $('#errors').append(`<p>${message}</p>`);

            }
        }
    )
}






