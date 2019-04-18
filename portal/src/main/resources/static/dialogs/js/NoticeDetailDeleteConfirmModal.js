; (function () {
    'use strict';
    var element = $('#deleteNoticeConfirmDialog');
    var NoticeConfirmDialog = {

        handlingConfirmDeleteOnClick: function () {
            element.on('click', '.btn-confirm-delete', function () {
                $.ajax(gbs.ajax.api.get("/notice/" + noticeId + "/delete")).then(function () {
                    element.modal('hide');
                }).fail(function (xhr) {
                    gbs.announceApiErrorResponse(xhr);
                });
            });
        },

        handlingModalOnClosing: function () {
            element.on('hide.bs.modal', function () {
                var activeElement = $(document.activeElement);
                if (!activeElement.is('[data-dismiss]')) {
                    $('#deleteNoticeSuccessDialog').modal('show');
                }
            });
        },

        run: function () {
            this.handlingConfirmDeleteOnClick();
            this.handlingModalOnClosing();
        }
    }
    $(document).ready(function () {
        NoticeConfirmDialog.run();
    });
})();