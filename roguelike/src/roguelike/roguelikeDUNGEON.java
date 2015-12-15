package roguelike;

import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class roguelikeDUNGEON {
	Random randomizer = new Random();
	int[][] dungeon = new int[64][64];
	
	private Image FLOOR, WALL, WALL_mossy, WALL_end_L, WALL_end_R;
	private Image CEILING, CEILING_bottom, CEILING_top, CEILING_right,
		CEILING_left, CEILING_center, CEILING_center_RL, CEILING_center_TB, CEILING_end_B, 
		CEILING_end_L, CEILING_end_R, CEILING_end_T, CEILING_i_BL, CEILING_i_BR, CEILING_i_TL,
		CEILING_i_TR, CEILING_o_BL, CEILING_o_BR, CEILING_o_TL, CEILING_o_TR;
	
	boolean room_iteration_complete = false;
	
	public roguelikeDUNGEON() {
		FLOOR = new ImageIcon("F:/workspace/roguelike/src/roguelike/floor.png").getImage();
		WALL = new ImageIcon("F:/workspace/roguelike/src/roguelike/wall.png").getImage();
		CEILING = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling.png").getImage();
		CEILING_bottom = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_bottom.png").getImage();
		CEILING_top = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_top.png").getImage();
		CEILING_left = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_left.png").getImage();
		CEILING_right = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_right.png").getImage();
		CEILING_center = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_center.png").getImage();
		CEILING_center_RL = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_center_RL.png").getImage();
		CEILING_center_TB = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_center_TB.png").getImage();
		CEILING_end_B = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_end_B.png").getImage();
		CEILING_end_L = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_end_L.png").getImage();
		CEILING_end_R = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_end_R.png").getImage();
		CEILING_end_T = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_end_T.png").getImage();
		CEILING_i_BL = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_i_BL.png").getImage();
		CEILING_i_BR = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_i_BR.png").getImage();
		CEILING_i_TL = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_i_TL.png").getImage();
		CEILING_i_TR = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_i_TR.png").getImage();
		CEILING_o_BL = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_o_BL.png").getImage();
		CEILING_o_BR = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_o_BR.png").getImage();
		CEILING_o_TL = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_o_TL.png").getImage();
		CEILING_o_TR = new ImageIcon("F:/workspace/roguelike/src/roguelike/ceiling_o_TR.png").getImage();
		
		
		
		
		
		dungeon_reset();
		
		int num_of_rooms = 10;
		int step = 0;
		while (step < num_of_rooms) {
			while (room_iteration_complete == false){
				//step = 0;
				make_rooms();
			}
			room_iteration_complete = false;
			System.out.println("Room pair #" + step + " generated.");
			step ++;
		}
		
		make_walls();
	}
	
	public void dungeon_reset() {
		for (int x = 0; x < 64; x++) {
			for (int y = 0; y < 64; y++) {
				dungeon[x][y] = 1;
			}
		}
	}
	
	public void draw(Graphics g) {
		for (int col = 0; col < 64; col++) {
			for (int row = 0; row < 64; row++) {
				if (test_tile(row,col) == 0) {
					g.drawImage(FLOOR, (col*8), (row*8), 8, 8, null);
				}
				if (test_tile(row,col) == 3) {
					g.drawImage(WALL, (col*8), (row*8), 8, 8, null);
				}
//				if (test_tile(row,col) == 1) {
//					g.drawImage(CEILING, (col*8), (row*8), 8, 8, null);
//				}
				
				/* Calculates which ceiling tile should be drawn */
//				if (test_tile(row,col) == 1 && row > 1 && col > 1 && row < 62 && col < 62) {
//					int counter = ceiling_checker(row, col);
//					switch (counter) {
//					case 1:
//						g.drawImage(CEILING_o_BL, (col*8), (row*8), 8, 8, null);
//						break;
//					case 2:
//						g.drawImage(CEILING_o_TR, (col*8), (row*8), 8, 8, null);
//						break;
//					case 3:
//						g.drawImage(CEILING_o_TL, (col*8), (row*8), 8, 8, null);
//						break;
//					case 4:
//						g.drawImage(CEILING_o_BR, (col*8), (row*8), 8, 8, null);
//						break;
//					case 5:
//						g.drawImage(CEILING, (col*8), (row*8), 8, 8, null);
//						break;
//					case 6:
//						g.drawImage(CEILING_top, (col*8), (row*8), 8, 8, null);
//						break;
//					case 7:
//						g.drawImage(CEILING_right, (col*8), (row*8), 8, 8, null);
//						break;
//					case 8:
//						g.drawImage(CEILING_bottom, (col*8), (row*8), 8, 8, null);
//						break;
//					case 9:
//						g.drawImage(CEILING_left, (col*8), (row*8), 8, 8, null);
//						break;
//					case 10:
//						g.drawImage(CEILING_i_BR, (col*8), (row*8), 8, 8, null);
//						break;
//					case 11:
//						g.drawImage(CEILING_i_BL, (col*8), (row*8), 8, 8, null);
//						break;
//					case 12:
//						g.drawImage(CEILING_i_TR, (col*8), (row*8), 8, 8, null);
//						break;
//					case 13:
//						g.drawImage(CEILING_i_TL, (col*8), (row*8), 8, 8, null);
//						break;
//					case 14:
//						g.drawImage(CEILING_end_L, (col*8), (row*8), 8, 8, null);
//						break;
//					case 15:
//						g.drawImage(CEILING_end_T, (col*8), (row*8), 8, 8, null);
//						break;
//					case 16:
//						g.drawImage(CEILING_end_R, (col*8), (row*8), 8, 8, null);
//						break;
//					case 17:
//						g.drawImage(CEILING_end_B, (col*8), (row*8), 8, 8, null);
//						break;
//					case 18:
//						g.drawImage(CEILING_center_RL, (col*8), (row*8), 8, 8, null);
//						break;
//					case 19:
//						g.drawImage(CEILING_center_TB, (col*8), (row*8), 8, 8, null);
//						break;
//					case 20:
//						g.drawImage(CEILING_center, (col*8), (row*8), 8, 8, null);
//						break;
//					}
//				}
			}
		}
	}
	
	public int ceiling_checker(int r, int c) {
		int row = r;
		int col = c;
		String tile = null;
		int tile_num = 0;
		
		if (dungeon[row-1][col-1] != 1) {
			tile = tile + "a";
		}
		if (dungeon[row-1][col] != 1) {
			tile = tile + "b";
		}
		if (dungeon[row-1][col+1] != 1) {
			tile = tile + "c";
		}
		if (dungeon[row][col+1] != 1) {
			tile = tile + "d";
		}
		if (dungeon[row+1][col+1] != 1) {
			tile = tile + "e";
		}
		if (dungeon[row+1][col] != 1) {
			tile = tile + "f";
		}
		if (dungeon[row+1][col-1] != 1) {
			tile = tile + "g";
		}
		if (dungeon[row][col-1] != 1) {
			tile = tile + "h";
		}
		
		return tile_num;
	}
	
	public void print_dungeon() {
		for (int x = 0; x < 64; x++) {
			System.out.println("");
			for (int y = 0; y < 64; y++) {
				System.out.print(dungeon[x][y] + " ");
			}
		}
	}
	
	public void make_rooms() {
		int x1=0, y1=0, x2=0, y2=0;
		
		//this makes one room. 'x1' and 'y1' are the top left coordinates, while 'x2' and 'y2' are the bottom right.
		boolean looping1 = true;
		int rm1_step = 0;
		
		
		System.out.println("Starting first room generation...");
		while (looping1) {
			
			looping1 = false;
			x1 = (randomizer.nextInt(51) + 3);//chooses a room size, max x location is 50. The oneis added so a room isn't right on the edge
			y1 = (randomizer.nextInt(51) + 3);//same as above, but for y value
			if (check_for_rooms(y1, x1)) {//checks if the space is occupied, and if it is, recalls method
				looping1 = true;
			}
			x2 = (x1 + randomizer.nextInt(6)+4);
			y2 = (y1 + randomizer.nextInt(6)+4);
			if (check_for_rooms(y2, x2)){
				looping1 = true;
			}
			rm1_step ++;
			System.out.println("Room 1 iteration: " + rm1_step);
			if (rm1_step > 10000) {
				looping1 = false;
				System.out.println("Program encountered infinite loop, and was terminated. Resetting dungeon...");
				//dungeon_reset();
				room_iteration_complete = false;
			}
		}
		
		for (int row = y1; row < y2; row++) {
			for (int col = x1; col < x2; col++) {
				dungeon[row][col] = 0;
			}
		}
		
		System.out.println("First room generated...");
		
		int x11=0, y11=0, x22=0, y22=0;
		
		//this will make another room with the same guideline as above. The coordinates from both will be used to make passages between them.
		boolean looping2 = true;
		int rm2_step = 0;
		
		System.out.println("Starting second room generation...");
		while (looping2) {
			
			looping2 = false;
			x11 = (randomizer.nextInt(51) + 3);
			y11 = (randomizer.nextInt(51) + 3);
			if (check_for_rooms(y11, x11)) {
				looping2 = true;
			}
			x22 = (x11 + randomizer.nextInt(6)+4);
			y22 = (y11 + randomizer.nextInt(6)+4);
			
			if (check_for_rooms(y22, x22)){
				looping2 = true;
			}
			rm2_step ++;
			System.out.println("Room 2 iteration: " + rm2_step);
			if (rm2_step > 10000) {
				looping2 = false;
				System.out.println("Program encountered infinite loop, and was terminated. Resetting dungeon...");
				//dungeon_reset();
				room_iteration_complete = false;
			}
		}
		
		for (int row = y11; row < y22; row++) {
			for (int col = x11; col < x22; col++) {
				dungeon[row][col] = 0;
			}
		}
		System.out.println("Second room generated...");
		
		int door1x = 0, door1y = 0, door2x = 0, door2y = 0;
		
		door1x = (randomizer.nextInt(x2-x1) + x1);
		door1y = (randomizer.nextInt(y2-y1) + y1);
		door2x = (randomizer.nextInt(x22-x11) + x11);
		door2y = (randomizer.nextInt(y22-y11) + y11);
		
		int rl_path = 0;
		//rl_path = 1 || first room is to the right of the second room
		//rl_path = 2 || first room is to the left of the second room
		int ud_path = 0;
		//ud_path = 1 || first room is above the second room
		//ud_path = 2 || first room is under the second room
		
		if (door1x > door2x) {
			rl_path = 1;//first is right of second
		} else {
			rl_path = 2;//first is left of second
		}
		
		if (door1y > door2y) {
			ud_path = 1;//first is above second
		} else {
			ud_path = 2;//first is below second
		}
		
		if (rl_path == 1) {
			for (int col = door2x; col <= door1x; col ++) {
				dungeon[door1y][col] = 0;
			}
		}
		if (rl_path == 2) {
			for (int col = door1x; col <= door2x; col ++) {
				dungeon[door1y][col] = 0;
			}
		}
		if (ud_path == 1) {
			for (int row = door2y; row <= door1y; row ++) {
				dungeon[row][door2x] = 0;
			}
		}
		if (ud_path == 2) {
			for (int row = door1y; row <= door2y; row ++) {
				dungeon[row][door2x] = 0;
			}
		}
		System.out.println("Room set generated.");
		room_iteration_complete = true;
	}
	
	public void make_walls() {
		for (int row = 0; row < 64; row++) {
			for (int col = 0; col < 64; col++) {
				if (dungeon[row][col] == 0) {
					if (dungeon[row-1][col] == 1) {
						dungeon[row-1][col] = 3;
						dungeon[row-2][col] = 1;
					}
				}
				if (row > 2) {
					if (dungeon[row-3][col] == 3){
					dungeon[row-1][col] = 0;
					dungeon[row-2][col] = 0;
					}
				}
				
			}
		} 
	}
	
	public int test_tile(int r, int c) {
		int row = r;
		int col = c;
		return dungeon[row][col];
	}
	
	public boolean check_for_rooms(int r, int c) {
		boolean occupied = false;
		
		for (int row = (r-1); row <= (r+1); row ++) {
			for (int col = (c-1); col <= (c+1); col ++) {
				if (test_tile(row, col) == 0) {
					occupied = true;
				}
			}
		}
		return occupied;
	}
}
