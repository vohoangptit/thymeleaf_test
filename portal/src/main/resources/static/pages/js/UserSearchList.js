; (function () {
    'use strict';

    var UserSearchList = {

        handlingUserListItemOnClick: function () {
            $(".table-row").click(function () {
                window.document.location = $(this).data("href");
            });
        },

        handlingClearButtonOnClick: function () {
            $('.btn-clear').on('click', function () {
                var searchConditions = $('#search-conditions');
                searchConditions.find('input[name=name]').val('').change();
                searchConditions.find('input[name=email]').val('').change();
            });
        },

        run: function () {
            this.handlingUserListItemOnClick();
            this.handlingClearButtonOnClick();
        }
    }

    $(document).ready(function () {
        UserSearchList.run();
    });
})();