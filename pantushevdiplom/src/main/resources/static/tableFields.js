function reloadTableList() {
    let tableName = $('#input-tableName').val();
    let page = $('#input-page').val();
    let size = $('#input-size').val();
    loadTablesList( tableName, page, size, false, false);
}

function loadTableListPrev() {
    let tableName = $('#input-tableName').val();
    let page = $('#input-page').val();
    let size = $('#input-size').val();
    loadTablesList(tableName, page, size, true, false);
}

function loadTableListNext() {
    let tableName = $('#input-tableName').val();
    let page = $('#input-page').val();
    let size = $('#input-size').val();
    loadTablesList(tableName, page, size, false, true);
}

function loadTablesList(tableName, page, size, prev, next) {
    let v_url = '/api/table/' + tableName + '/field';

    let firstParam = true;
    if (page != 0) {
        if (firstParam) {
            v_url += '?';
            firstParam = false;
        }
        else {
            v_url += '&';
        }
        v_url += 'page=' + page;
    }
    if (prev) {
        if (firstParam) {
            v_url += '?';
            firstParam = false;
        }
        else {
            v_url += '&';
        }
        v_url += 'prev=true';
    }
    else if (next) {
        if (firstParam) {
            v_url += '?';
            firstParam = false;
        }
        else {
            v_url += '&';
        }
        v_url += 'next=true';
    }
    if (size != 0) {
        if (firstParam) {
            v_url += '?';
        }
        else {
            v_url += '&';
        }
        v_url += 'size=' + size;
    }
    $.get(v_url).done(function (entities) {
        $('#tables-list > tbody').empty();

        entities.content.forEach(function (enitity) {
            $('#tables-list > tbody').append(`
                    <tr>
                        <td>${enitity.idFldName}</td>
                        <td>${enitity.seqNum}</td>
                        <td>${enitity.requiredSw}</td>
                        <td>${enitity.nullableSw}</td>
                        <td>${enitity.validateSw}</td>
                        <td>
                            <a href="/tableFieldLangs?table_name=${enitity.idTblName}&field_name=${enitity.idFldName}">i18s</a>
                        </td>
                        <td>
                            <a href="/table/edit/${enitity.id}">Редактировать</a>
                        </td>
                        <td>
                            <a href="#" onclick="deleteTable(${enitity.id})">Удалить</a>
                        </td>
                    </tr>
                `)
        });
        $('#input-page').val(entities.number);
        $('#input-size').val(entities.size);
        $('#button-prev').attr('disabled', entities.first);
        $('#button-next').attr('disabled', entities.last);
        $('#text-totalPages').text(entities.totalPages);
    })
}





