public class KeyThread implements Runnable{
    
    private Screen s;
    public KeyThread(Screen s){
        this.s = s;
    }

    public void run(){
        while (true){
            while (!s.getMoveRight() && !s.getMoveLeft() && !s.getDropPiece() && !s.getClockWise()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (s.getMoveRight()){
                s.moveRight();
            }
            if (s.getMoveLeft()){
                s.moveLeft();
            }
            if (s.getDropPiece()){
                s.dropPiece();
            }
            if (s.getClockWise()){
                s.clockWise();
                // System.out.println("ss");
            }


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}