import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener,KeyListener {
    Timer t=new Timer(1,this);
    int x=0;int vel=1;int vel2=1;int vel3=1;int m=0;int xc=0;int n=0;int y=500;int worldWid=8000;

    private String path="";

    int eneX[]={262*6,262*6+100,262*11,262*11+100,262*13,262*13+100,262*19,262*19+100,262*21,262*21+100,262*23,262*23+100,262*25,262*25+100};
    int pipeX[]={300,750,1300,2000,2550,3751,4300,4850,6900,7455};
    int pipeY[]={400,350,400,350,350,400,350,400,400,370};


    int bridgeX[]={262*6+30,262*11+30,262*13+30,262*19+30,262*21+30,262*23+30,262*25+30};
    int bridgeX2[]={262*6+60,262*11+320,262*19+320,262*21+320,262*23+320};
    int bridgeX3[]={480,1020,2230,3960,4580,7100};

    int GeneX[]={815,2062,3815,4364,6964};

    int AirEneX[]={262*11+370,262*19+370,262*21+370,262*23+370};


    int im=3;int xm=400;int mf=0;int upperH;int lowerH=446;   int ym=lowerH-1;

    Game(){

        this.setBackground(Color.black);
        key();
    }

    public final  void key(){
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.setBounds(0, 0, worldWid, 1200);

        this.setLocation(xc+x, 0);


        patterns(g);
        pipes(g);
        bricks(g);

        water(g);
        WaterEnemies(g);
        bridges(g);
        groundEne(g);
        airEne(g);
        Mario(g);
        // t.start();

    }

    public void Mario(Graphics g){
        ImageIcon i=new ImageIcon("");
        g.fillRect(xm+mf, ym+im, 50, 50);
        g.setColor(Color.gray);
    }

    int yc=150;
    public void airEne(Graphics g){
        ImageIcon i1=new ImageIcon("C:\\Users\\pic\\ene1.png");
        int i=0;

        while(i<AirEneX.length){
            // g.fillRect(AirEneX[i], yc, 50, 50);
            i1.paintIcon(this,g,AirEneX[i], yc);
            i++;
        }


    }

    @Override
    public void actionPerformed(ActionEvent e){

        //1. uni<262*6-3 || uni>262*6+219

        if(eneX[0]<262*6-1 || eneX[0]>262*7-168)
        {vel=-vel;}
        for(int g=0; g<eneX.length; g++){ eneX[g]+=vel;}
        if(GeneX[0]<815 || GeneX[0]>1229){vel2=-vel2;}
        for(int i=0; i<GeneX.length; i++){
            GeneX[i]+=vel2;}

        if(yc<150 || yc>396){vel3=-vel3;}
        yc+=vel3;

        ym-=im;int n=2;

        if(ym<upperH || ym>=lowerH){im=-im;}
        if(ym>lowerH){im=0;}

        repaint();


    }


    public void fall(){

    }

    public void groundEne(Graphics g){
        ImageIcon i1=new ImageIcon("C:\\Users\\pic\\ene2.png");
        for(int i=0; i<GeneX.length; i++){
            //g.fillRect(GeneX[i], 450, 50, 50);
            i1.paintIcon(this,g,GeneX[i], 427);
        }
    }


    public void bridges(Graphics g){
        ImageIcon c=new ImageIcon("C:\\Users\\pic\\bridge.png");
        ImageIcon c2=new ImageIcon("C:\\Users\\pic\\block1.png");
        ImageIcon c3=new ImageIcon("C:\\Users\\pic\\block3.png");

        int yc;
        for(int i=0; i<bridgeX.length; i++){
            c.paintIcon(this, g, bridgeX[i], 250);
            // if(mf+xm>=bridgeX[i] && mf+xm<=bridgeX[i]+200){upperH=320;}
            //  if( mf+xm>bridgeX[i]+200 || mf+xm<bridgeX[0]){upperH=0;}
        }

        // if(mf+xm>=bridgeX[0] && mf+xm<bridgeX[0]+200 && ym+im<200){lowerH=200;}
        //   if(mf+xm<bridgeX[0] && ym+im<=200 || mf+xm>bridgeX[0]+200 && ym+im<=200){}

        for(int i=0; i<bridgeX2.length; i++){
            if(i==0){yc=60;}else {yc=120;}
            c2.paintIcon(this, g, bridgeX2[i], yc);}

        for(int i=0; i<bridgeX3.length; i++){
            c3.paintIcon(this, g, bridgeX3[i], 200);}
    }



    public void patterns(Graphics g){
        int ycoor=52,xcoor=0;
        ImageIcon c=new ImageIcon("C:\\Users\\pic\\blocks2.png");
        for(int j=0; j<5; j++){
            for(int i=0; i<15; i++){
                c.paintIcon(this, g, xcoor, ycoor);
                ycoor+=30;
            }
            xcoor+=30;
            if(j==0){ycoor=82;}
            if(j==1){ycoor=112+90;}
            if(j==2){ycoor=142+90;}
            if(j==3){ycoor=172+90*4;}
        }
        for(int i=0; i<5*30; i+=30){ c.paintIcon(this, g, 120+i,472);}
        c.paintIcon(this, g,120+4*30,442);
        c.paintIcon(this, g,120+4*30,412);
        c.paintIcon(this, g,120+3*30,442);
    }

    public void pipes(Graphics g){
        ImageIcon i=new ImageIcon("C:\\Users\\pic\\pipes4.png");
        for(int k=0; k<pipeX.length; k++){
            i.paintIcon(this, g, pipeX[k], pipeY[k]);}
    }

    public void WaterEnemies(Graphics g){
        ImageIcon i1=new ImageIcon("C:\\Users\\pic\\world1 water enemies.png");
        ImageIcon i2=new ImageIcon("C:\\Users\\pic\\world1 water enemies flipped.png");
        g.setColor(Color.MAGENTA);
        int i=0;
        while(i<eneX.length){

            {   i1.paintIcon(this,g,eneX[i],620);}

            if(vel>0) {   i2.paintIcon(this,g,eneX[i],620);}
            i++;
        }
    }

    public void cuts(int x1){
        if(x1>=262*6 && x1<262*7
                || x1>=262*11 && x1<262*12
                || x1>=262*13 && x1<262*14
                || x1>=262*19 && x1<262*20
                || x1>=262*21 && x1<262*22
                || x1>=262*23 && x1<262*24
                || x1>=262*25 && x1<262*26)
        {y=700;}else {y=500;}
    }

    public void bricks(Graphics g){
        ImageIcon i1=new ImageIcon("C:\\Users\\pic\\grass3.png");

        int x1=0;
        for(int k=0;k<29; k++){
            cuts(x1);
            i1.paintIcon(this, g, x1, y);
            x1+=170*2-78;
        }
    }

    public void water(Graphics g){
        int[]a={6,11,13,19,21,23,25};
        ImageIcon i2=new ImageIcon("C:\\Users\\pic\\water4.png");
        for(int i=0; i<a.length; i++){
            i2.paintIcon(this, g, 262*a[i]-1, 525);}
    }






    @Override
    public void keyPressed(KeyEvent e){

        int code=e.getKeyCode();
        if(code==KeyEvent.VK_RIGHT){if(x>=worldWid-2000){x-=0;}else{x-=10;}mf+=10;}

        if(code==KeyEvent.VK_LEFT){   if(x==0){x+=0;mf-=0;}else{x+=10;mf-=10;}}
        if(code==KeyEvent.VK_SPACE){
            if(ym>=lowerH-1)  im=5;
            t.start();//x-=30;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}


    public static void main(String[] args) {

        Game m=new Game();

        JFrame ft=new JFrame();
        ft.add(m);
        ft.setSize(1365,725);
        ft.setVisible(true);
        ft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

