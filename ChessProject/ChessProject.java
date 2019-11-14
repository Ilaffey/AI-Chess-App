import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
CA2 - Introduction to Artificial Intelligence
Student Name: Ian Laffey
Student Number: x1272931
*/


/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
  Boolean MakeYourMove;
  Boolean TakingTurns;




    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);

      TakingTurns  = false;
      MakeYourMove = true;

    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			oponent = true;
      // if opponent name contains "King": it's valid move, show message of victory and exit program
      if(tmp1.contains("King")){
        JOptionPane.showMessageDialog(null,"White Wins!");
        System.exit(0);
      }
		}
		else{
			oponent = false;
		}
		return oponent;
	}

  /*
		This is a method to check if a piece is a White piece.
	*/

  private Boolean checkBlackOponent(int newX, int newY){//take in coordinates
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;//has to be label present sitting on that location
		String tmp1 = awaitingPiece.getIcon().toString();//get icon from label, cast to string
		if(((tmp1.contains("White")))){// if the string contains white, it is oponent, else it's not
			oponent = true;
      if(tmp1.contains("King")){
        // if opponent name contains "King": it's valid move, show message of victory and exit program
        JOptionPane.showMessageDialog(null,"Black Wins!");
        System.exit(0);
      }
		}
		else{
			oponent = false;
		}
		return oponent;
	}
// Use similar code to above
//Take coordinates of string nameofpiece
//Method to check if a piece is present
  private String NameOfPiece(int newX, int newY){
 Component c = chessBoard.findComponentAt(newX, newY);
 if(c instanceof JLabel){
   JLabel awaitingPiece = (JLabel) c;//has to be label present sitting on that location
   String tmp1 = awaitingPiece.getIcon().toString();//get icon from label, cast to string
   return tmp1;
 }
 else{
   return "";
 }
}

  //Method stating that attacking the enemy piece is a valid move
  //Changing methods for attacking opponents as Queen's move has become too complicated, will transfer onto other pieces too
    private Boolean AttackEnemyPiece(int newX, int newY, String pieceName){
      Boolean validMove = false;
      if(piecePresent(newX, newY)){
          if(pieceName.contains("White")){
            if(checkWhiteOponent(newX, newY)){
              validMove = true;
              return validMove;
            }
            else{
              validMove = false;
              return validMove;
            }
          }
          else{
            if(checkBlackOponent(newX, newY)){
              validMove = true;
              return validMove;
            }
            else{
              validMove = false;
              return validMove;
            }
          }
        }
      else{
        validMove = true;
        return validMove;
        }
    }


  //Method for checking surrounding squares of king, in 1 square distances in every direction and where name of piece includes "King"
  //Each if statment use two squares, either side of king. example: 1st statment focuses on square in front and the square behind the king in Y direction
            private Boolean OppositeKing(int newX, int newY){
            if((piecePresent(newX, newY+75)&& NameOfPiece(newX, newY+75).contains("King")||(piecePresent(newX, newY-75)&& NameOfPiece(newX, newY-75).contains("King")))){
              return  true;
            }
            else if((piecePresent(newX+75,newY)&& NameOfPiece(newX+75, newY).contains("King")||(piecePresent(newX-75, newY)&& NameOfPiece(newX-75, newY).contains("King")))){
              return true;
            }
            else if((piecePresent(newX-75, newY+75)&& NameOfPiece(newX-75, newY+75).contains("King")||(piecePresent(newX+75, newY-75)&& NameOfPiece(newX+75, newY-75).contains("King")))){
              return  true;
            }
            else if((piecePresent(newX-75, newY-75)&& NameOfPiece(newX-75, newY-75).contains("King")||(piecePresent(newX+75, newY+75)&& NameOfPiece(newX+75, newY+75).contains("King")))){
              return  true;
            }
            return false;
          }

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

    //Decalre Variables below - was getting unknown variable error for line else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);

    int distance = Math.abs(startX-landingX);

    int xMovement = Math.abs((landingX-startX));
    int yMovement = Math.abs((landingY-startY));
// got error, "progression cannot find symbol", need to declare below for when the black pawn makes it to end of board and becomes Queen
     Boolean progression = false;

//for each piece to take turns.
//if white piece has not made an x and y move then its that piece's turn else it's the black piece's. White piece moves as statement starts with it

if(MakeYourMove){
  		if(pieceName.contains("White") && !(xMovement == 0 && yMovement == 0)){
  			TakingTurns = true;
  		}
  	}
  	else{
  		if(pieceName.contains("Black") && !(xMovement == 0 && yMovement == 0)){
  			TakingTurns = true;
  		}
  	}
if(TakingTurns){// start wrapper for pieces taking turns in game
if(pieceName.contains("King")){

        Boolean inTheWay = false;

          //Invalid move if king moves greater than 1 square or moves 0 squares
          if(xMovement > 1 || yMovement > 1 || (xMovement == 0 && yMovement == 0)){
            //Invalid move if landing is less than 0 squares or greater than 7 squares
          if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){// Stops the piece from going off board, will be used for other pieces
          validMove = false;
        }
      }

          else{
            // Use OppositeKing method, opposite king can't be 1 square away from landing square of move and other piece can'be present
            if(!(OppositeKing(e.getX(), e.getY()))){
              if(!piecePresent(e.getX(), e.getY())){
                validMove = true;
              }
              else{
                validMove = AttackEnemyPiece(e.getX(), e.getY(), pieceName);
              }
            }
          }

      }


//Queen's has both the moves of a Rook and Bishop
//The Queen can move in a horizontal, vertical or diagonal direction if no pieces present
else if(pieceName.contains("Queen")){

      Boolean inTheWay = false;
      //Invalid move if landing is less than 0 squares or greater than 7 squares
      if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
        validMove = false;
      }

      //Queen can move like a Rook so used same code
      //Queen can move horzontially or vertically

      else if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
          if(Math.abs(startX-landingX)!=0){
          if(startX-landingX > 0){
            for(int i = 0; i < xMovement; i++){
              if(piecePresent(initialX-(i*75), e.getY())){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
          else{
            for(int i=0; i < xMovement; i++){
              if(piecePresent(initialX+(i*75),e.getY())){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
        }
        else{
          if(startY-landingY > 0){
            for(int i=0; i < yMovement; i++){
              if(piecePresent(e.getX(),initialY-(i*75))){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
          else{
            for(int i=0; i < yMovement; i++){

              if(piecePresent(e.getX(),initialY+(i*75))){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
        }

        if(inTheWay){
          validMove = false;
        }

        else{
          //Checks if the piece in the way is an opponent piece and returns a Boolean
          validMove = AttackEnemyPiece(e.getX(), e.getY(), pieceName);
        }
      }


      //Queen can also  move like a Bishop, used same code
      //Queen can move diagonally

      else{
        if(Math.abs(startX-landingX) == Math.abs(startY-landingY)){
          if((startX-landingX < 0)&&(startY-landingY < 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX < 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)),initialY-(i*75))){
                inTheWay=true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)),initialY-(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY < 0 )){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          if(inTheWay){
            validMove = false;
          }
          else{
            validMove = AttackEnemyPiece(e.getX(), e.getY(), pieceName);
          }
        }

        else{
          validMove = false;
        }
      }
    }




else if(pieceName.contains("Bishup")){
  //declare variable inTheWay for Bishup
  Boolean inTheWay = false;
  //Invalid move if landing is less than 0 squares or greater than 7 squares
  if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
  validMove = false;
      }
      else{
        validMove = true;
        if(Math.abs(startX-landingX) == Math.abs(startY-landingY)){
          if((startX-landingX < 0)&&(startY-landingY < 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX < 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)),initialY-(i*75))){
                inTheWay=true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)),initialY-(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY < 0 )){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          if(inTheWay){
            validMove = false;
          }
          else{
            //If opponent piece, Bishup can attack
            validMove = AttackEnemyPiece(e.getX(), e.getY(), pieceName);
          }
        }
        else{
          validMove = false;
        }
      }
    }


else if(pieceName.contains("Rook")){
  //declare variable intheway for Rook
  Boolean intheway = false;
  //Invalid move if landing is less than 0 squares or greater than 7 squares
  if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
        validMove = false;
      }
      else{
        if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
          if(Math.abs(startX-landingX)!=0){
            if(startX-landingX > 0){
              for(int i = 0; i < xMovement; i++){
                if(piecePresent(initialX-(i*75), e.getY())){
                  intheway = true;
                  break;
                }
                else{
                  intheway = false;
                }
              }
            }
            else{
              for(int i=0; i < xMovement; i++){
                if(piecePresent(initialX+(i*75),e.getY())){
                  intheway = true;
                  break;
                }
                else{
                  intheway = false;
                }
              }
            }
          }
          else{
            if(startY-landingY > 0){
              for(int i=0; i < yMovement; i++){
                if(piecePresent(e.getX(),initialY-(i*75))){
                  intheway = true;
                  break;
                }
                else{
                  intheway = false;
                }
              }
            }
            else{
              for(int i=0; i < yMovement; i++){
                if(piecePresent(e.getX(),initialY+(i*75))){
                  intheway = true;
                  break;
                }
                else{
                  intheway = false;
                }
              }
            }
          }

          if(intheway){
            validMove = false;
          }
          else{
            //Checks if the piece in the way is an opponent piece and returns a Boolean
            validMove = AttackEnemyPiece(e.getX(), e.getY(), pieceName);
          }
        }
        else{
          validMove = false;
        }
      }
    }

    else if(pieceName.contains("Knight")){
      //Invalid move if landing is less than 0 squares or greater than 7 squares
      if(((landingX < 0)||(landingX > 7))||((landingY < 0)||(landingY > 7))){
        validMove = false;
      }
      else{ //  Need to map out all possibilities for any L shape from any position
        if(((landingX == startX+1)&&(landingY == startY+2))||((landingX == startX-1)&&(landingY ==
  			startY+2))||((landingX == startX+2) && (landingY == startY+1))||((landingX == startX-2) &&(landingY ==
  			startY+1))||((landingX == startX+1) && (landingY == startY-2))||((landingX == startX-1) &&(landingY ==
  			startY-2))||((landingX == startX+2) && (landingY == startY-1))||((landingX == startX-2) &&(landingY ==
  			startY-1))){
          if(piecePresent(e.getX(),(e.getY()))){
            if(pieceName.contains("White")){
              if(checkWhiteOponent(e.getX(), e.getY())){
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
            else{
              if(checkBlackOponent(e.getX(), e.getY())){
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
          }
          else{
        validMove = true;
      }
    }
      else{
          validMove = false;
    }
  }
}

/*

  So a Pawn is able to move two squares forward one its first go but only one square after that.
  The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
  a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
  square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
  If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
  demonstration purposes the Pawn here turns into a Queen.
*/
    else if(pieceName.equals("BlackPawn")){

      //Invalid move if landing is less than 0 squares or greater than 7 squares
      if(((landingX < 0)||(landingX > 7))||((landingY < 0)||(landingY > 7))){
        validMove = false;
      }
      else{//From starting position
        if((startY == 6)&&(startX == landingX)&&(((startY-landingY)== 1)||(startY-landingY)== 2)){// BlackPawn can move either one or two squares in Y direction

    //if there is not another piece in the way
              if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()+75))))

              validMove = true;

        else{
                validMove = false;
              }
        }

        else if((Math.abs(startX-landingX)== 1)&&(((startY-landingY)== 1))){//if move is one square in x and y direction/diagonal
              if(piecePresent(e.getX(),e.getY())){
                  if(checkBlackOponent(e.getX(),e.getY())){
                    validMove = true;
                    if(landingY == 0){
                      progression = true;
          }
        }

        else{
          validMove = false;
        }
      }
      else{
        validMove = false;
      }
    }
      else if ((startY != 6)&&((startX == landingX)&&(((startY-landingY)==1)))){
        //if piece in the way
        if(!piecePresent(e.getX(), e.getY())){
          validMove = true;
          if(landingY == 0){
            progression = true;
          }
        }
        else{
          validMove = false;
        }
      }
        else{
          validMove = false;
        }
      }

  }

//Copied blackpawn code for white code. just switch coordinates around
		else if(pieceName.equals("WhitePawn")){
      //Invalid move if landing is less than 0 squares or greater than 7 squares
      if(((landingX < 0)||(landingX > 7))||((landingY < 0)||(landingY > 7))){
        validMove = false;
      }  //From starting position, if((startY == 6) becomes if((startY == 1)
        //if moving 1 squre or 2 squares
      else{
      if((startY == 1)&&(startX == landingX)&&(((landingY - startY) == 1) || (landingY - startY) == 2)){
        //if there is not another piece in the way, e.getY()+75 becomes e.getY()-75
     if((!piecePresent(e.getX(), e.getY())&&(!piecePresent(e.getX(), e.getY()-75)))){
       validMove = true;
     }
   }
   else if((Math.abs(landingX - startX) == 1)&&(((landingY - startY) == 1))){
     if (piecePresent(e.getX(), e.getY())) {
       if(checkWhiteOponent(e.getX(), e.getY())){
         validMove = true;
         // get to end of board -  (landingY == 0) becomes (landingY == 7) as that is the other postion wehre the board ends in y direction
         if(landingY == 7){
           success = true; // when becominhg Queen
         }
       }
       else{
         validMove = false;
       }
     }
     else{
       validMove = false;
     }
   }//when not in starting position
   else if((startY != 1)&&((startX == landingX)&&(((landingY - startY) == 1)))){
     if(!piecePresent(e.getX(), e.getY())){
       validMove = true;
       // get to end of board -  (landingY == 0) becomes (landingY == 7) as that is the other postion wehre the board ends in y direction
       if(landingY == 7){
         success = true;// when becominhg Queen
       }
     }
     else{
       validMove = false;
     }
   }
   else{
     validMove = false;
   }
  }
}
}// TakingTurns wrapper ends


		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}

        else{// piece cannot turn to move
          MakeYourMove = !MakeYourMove;
          TakingTurns = false;

          //making black queen when progression is true and pawn makes it to landing Y=0 on board
            if(progression){
            int location = 0 + (e.getX()/75);
            if (c instanceof JLabel){
              Container parent = c.getParent();
              parent.remove(0);
              pieces = new JLabel( new ImageIcon("BlackQueen.png") );
              parent = (JPanel)chessBoard.getComponent(location);
              parent.add(pieces);
            }
            else{
    					Container parent = (Container)c;
    	            	pieces = new JLabel( new ImageIcon("BlackQueen.png") );
    					parent = (JPanel)chessBoard.getComponent(location);
    			    	parent.add(pieces);
    				}
          }

		else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);
			}
		}
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
