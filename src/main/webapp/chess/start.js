var myBoard = Chessboard('myBoard', {
    draggable: true,
    dropOffBoard: 'trash',
    sparePieces: true
    })

    $('#startBtn').on('click', myBoard.start)
    $('#clearBtn').on('click', myBoard.clear)