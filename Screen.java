import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

import java.util.ArrayList;


public class Screen extends JPanel implements ActionListener,KeyListener{
    private GameEngine engine;
    private final int SIZE = 30;
    private int keyPressed;

    private boolean moveRight, moveLeft, dropPiece, counterClockWise, clockWise;

    public Screen(){
        this.setFocusable(true);
        this.setLayout(null);
        addKeyListener(this);


        new Thread(new Animate(this)).start();
        new Thread(new KeyThread(this)).start();

        engine = new GameEngine();
        new Thread(engine).start();

        keyPressed = 0;
    }

    public Dimension getPreferredSize(){
		return new Dimension(30* SIZE, 21*SIZE);
	}

    public void paintComponent(Graphics g){
        super.paintComponent(g);


        g.setColor(new Color(200,200,200));
        g.fillRect(10*SIZE, 0, 10*SIZE, 21*SIZE);

        g.setColor(new Color(0,0,0));
        for (int i = 0; i < 10; i++){
            for (int e = 0; e < 21; e++){
                g.drawRect((10+i)*SIZE, e * SIZE, SIZE, SIZE);
            }
        }




        ArrayList<char[]> board = engine.getBoard();
        Piece current = engine.getCurrent();


        for (int i = 0; i < 20; i++){
            for (int e = 0; e < 10; e++){
                switch (board.get(i)[e]){
                    case 't':
                        g.setColor(new Color(164, 62, 154));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                    case 'o':
                        g.setColor(new Color(178, 152, 49));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                    case 'i':
                        g.setColor(new Color(49, 179, 130));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                    case 's':
                        g.setColor(new Color(160, 228, 47));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                    case 'z':
                        g.setColor(new Color(179, 51, 58));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                    case 'l':
                        g.setColor(new Color(179, 99, 50));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                    case 'j':
                        g.setColor(new Color(78, 61, 164));
                        g.fillRect(e*SIZE + 10*SIZE, (20-i)*SIZE, SIZE,SIZE);
                        break;
                }
            }
        }

        switch (current.getType()){
            case 't':
                g.setColor(new Color(164, 62, 154));
                break;
            case 'o':
                g.setColor(new Color(178, 152, 49));
                break;
            case 'i':
                g.setColor(new Color(49, 179, 130));
                break;
            case 's':
                g.setColor(new Color(160, 228, 47));
                break;
            case 'z':
                g.setColor(new Color(179, 51, 58));
                break;
            case 'l':
                g.setColor(new Color(179, 99, 50));
                break;
            case 'j':
                g.setColor(new Color(78, 61, 164));
                break;
        }

        g.fillRect(current.getFirst().getX()*SIZE + 10*SIZE, (20-current.getFirst().getY())*SIZE, SIZE,SIZE);
        g.fillRect(current.getSecond().getX()*SIZE + 10*SIZE, (20-current.getSecond().getY())*SIZE, SIZE,SIZE);
        g.fillRect(current.getThird().getX()*SIZE + 10*SIZE, (20-current.getThird().getY())*SIZE, SIZE,SIZE);
        g.fillRect(current.getFourth().getX()*SIZE + 10*SIZE, (20-current.getFourth().getY())*SIZE, SIZE,SIZE);

        g.setColor(new Color(0,0,0));
        g.drawRect(current.getFirst().getX()*SIZE + 10*SIZE, (20-current.getFirst().getY())*SIZE, SIZE,SIZE);
        g.drawRect(current.getSecond().getX()*SIZE + 10*SIZE, (20-current.getSecond().getY())*SIZE, SIZE,SIZE);
        g.drawRect(current.getThird().getX()*SIZE + 10*SIZE, (20-current.getThird().getY())*SIZE, SIZE,SIZE);
        g.drawRect(current.getFourth().getX()*SIZE + 10*SIZE, (20-current.getFourth().getY())*SIZE, SIZE,SIZE);
    }

    public void addImage(Graphics g, String adress, int x, int y, int size){
        Image image;
        try {
            image = ImageIO.read(new File(adress));
            g.drawImage(image, x, y, size,size,this);

        } catch (Exception e) {}
    }

    public void playSound(String fileName){
        File file = new File(fileName);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {}
    }

    public int getKeyPressed(){
        return keyPressed;
    }

    public void actionPerformed(ActionEvent e){
       
    }

    public void moveRight(){
        engine.moveRight();
    }

    public void moveLeft(){
        engine.moveLeft();
    }

    public void dropPiece(){
        engine.dropPiece();
    }

    public void clockWise(){
        engine.clockWise();
    }

    public void counterClockWise(){
        engine.counterClockWise();
    }

    public boolean getMoveRight(){
        return moveRight;
    }

    public boolean getMoveLeft(){
        return moveLeft;
    }

    public boolean getDropPiece(){
        return dropPiece;
    }

    public boolean getClockWise(){
        return clockWise;
    }

    public boolean getCounterClockWise(){
        return counterClockWise;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        keyPressed = 1;
        if (e.getKeyCode() == 39){
            moveRight = true;
        }
        if (e.getKeyCode() == 37){
            moveLeft = true;
        }
        if (e.getKeyCode() == 40){
            dropPiece = true;
        }
        if (e.getKeyCode() == 38){
            clockWise = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        keyPressed = 0;

        if (e.getKeyCode() == 39){
            moveRight = false;
        }
        if (e.getKeyCode() == 37){
            moveLeft = false;
        }
        if (e.getKeyCode() == 40){
            dropPiece = false;
        }
        if (e.getKeyCode() == 38){
            clockWise = false;
        }
    }
    public void keyTyped(KeyEvent e) {}


}