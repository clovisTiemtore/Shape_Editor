package main;

import formes.*;
import operations.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
	
    System.out.println("Welcome to Shape Editor !");

    while (true) {
            System.out.println("\nWhat do you want to draw ? (example : circle, union rectangle, etc) or q: to quit");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Goodbye !");
                break;
            }

            switch (input.toLowerCase()) {
                case "circle":
                    Circle(scanner);
                    break;
                
                case "rectangle":
                    Rect(scanner);
                    break;

                case "union circle":
                    unionCircle(scanner);
                    break;

                case "difference circle":
                    diffCircle(scanner);
                    break;

                case "intersection circle":
                    interCircle(scanner);
                    break;

                case "union rectangle":
                    unionRect(scanner);
                    break;

                case "difference rectangle":
                    diffRect(scanner);
                    break;

                case "intersection rectangle":
                    interRect(scanner);
                    break;

                default:
                    System.out.println("Invalid command. Try Again.");
            }
        }
        scanner.close();
    }

    private static void Circle(Scanner scanner) {
		System.out.println("circle(cx,cy,radius)");
		String input = scanner.nextLine().trim();
		if (!input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format. Please use the format circle(cx,cy,radius).");
			return;
		}
		String[] parts = input.substring(7, input.length() - 1).split(",");
		int cx = Integer.parseInt(parts[0].trim());
		int cy = Integer.parseInt(parts[1].trim());
		int radius = Integer.parseInt(parts[2].trim());
		Circle circle = new Circle(cx, cy, radius);
		System.out.println("\n" + circle.paint());
    }

    private static void Rect(Scanner scanner) {
		System.out.println("rectangle(x1,y1,x2,y2)");
		String input = scanner.nextLine().trim();
		if (!input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] parts = input.substring(10, input.length() - 1).split(",");
		int x1 = Integer.parseInt(parts[0].trim());
		int y1 = Integer.parseInt(parts[1].trim());
		int x2 = Integer.parseInt(parts[2].trim());
		int y2 = Integer.parseInt(parts[3].trim());
		Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
		System.out.println("\n" + rectangle.paint());
    }

	private static void unionCircle(Scanner scanner) {
		System.out.println("circle1(cx,cy,radius)");
		String circle1Input = scanner.nextLine().trim();
		if (!circle1Input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] circle1Parts = circle1Input.substring(7, circle1Input.length() - 1).split(",");
		int cx1 = Integer.parseInt(circle1Parts[0].trim());
		int cy1 = Integer.parseInt(circle1Parts[1].trim());
		int radius1 = Integer.parseInt(circle1Parts[2].trim());
	
		System.out.println("circle2(cx,cy,radius)");
		String circle2Input = scanner.nextLine().trim();
		if (!circle2Input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] circle2Parts = circle2Input.substring(7, circle2Input.length() - 1).split(",");
		int cx2 = Integer.parseInt(circle2Parts[0].trim());
		int cy2 = Integer.parseInt(circle2Parts[1].trim());
		int radius2 = Integer.parseInt(circle2Parts[2].trim());
	
		Circle circle1 = new Circle(cx1, cy1, radius1);
		Circle circle2 = new Circle(cx2, cy2, radius2);
	
		UnionCircle unionCircle = new UnionCircle();
		Circle unionResult = (Circle) unionCircle.apply(circle1, circle2);
		System.out.println("Result :\n" + unionResult.paint());
	}	
	
	private static void diffCircle(Scanner scanner) {
		System.out.println("circle1(cx,cy,radius)");
		String circle1Input = scanner.nextLine().trim();
		if (!circle1Input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] circle1Parts = circle1Input.substring(7, circle1Input.length() - 1).split(",");
		int cx1 = Integer.parseInt(circle1Parts[0].trim());
		int cy1 = Integer.parseInt(circle1Parts[1].trim());
		int radius1 = Integer.parseInt(circle1Parts[2].trim());
	
		System.out.println("circle2(cx,cy,radius)");
		String circle2Input = scanner.nextLine().trim();
		if (!circle2Input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] circle2Parts = circle2Input.substring(7, circle2Input.length() - 1).split(",");
		int cx2 = Integer.parseInt(circle2Parts[0].trim());
		int cy2 = Integer.parseInt(circle2Parts[1].trim());
		int radius2 = Integer.parseInt(circle2Parts[2].trim());
	
		Circle circle1 = new Circle(cx1, cy1, radius1);
		Circle circle2 = new Circle(cx2, cy2, radius2);
	
		DiffCircle diffCircle = new DiffCircle();
		Circle diffResult = (Circle) diffCircle.apply(circle1, circle2);
		System.out.println("Result :\n" + diffResult.paint());
	}
	
	private static void interCircle(Scanner scanner) {
		System.out.println("circle1(cx,cy,radius)");
		String circle1Input = scanner.nextLine().trim();
		if (!circle1Input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] circle1Parts = circle1Input.substring(7, circle1Input.length() - 1).split(",");
		int cx1 = Integer.parseInt(circle1Parts[0].trim());
		int cy1 = Integer.parseInt(circle1Parts[1].trim());
		int radius1 = Integer.parseInt(circle1Parts[2].trim());
	
		System.out.println("circle2(cx,cy,radius)");
		String circle2Input = scanner.nextLine().trim();
		if (!circle2Input.matches("^circle\\((\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] circle2Parts = circle2Input.substring(7, circle2Input.length() - 1).split(",");
		int cx2 = Integer.parseInt(circle2Parts[0].trim());
		int cy2 = Integer.parseInt(circle2Parts[1].trim());
		int radius2 = Integer.parseInt(circle2Parts[2].trim());
	
		Circle circle1 = new Circle(cx1, cy1, radius1);
		Circle circle2 = new Circle(cx2, cy2, radius2);
	
		InterCircle interCircle = new InterCircle();
		Circle interResult = (Circle) interCircle.apply(circle1, circle2);
		System.out.println("Result :\n" + interResult.paint());
	}
	
	private static void unionRect(Scanner scanner) {
		System.out.println("rectangle1(x1,y1,x2,y2)");
		String rectangle1Input = scanner.nextLine().trim();
		if (!rectangle1Input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] rectangle1Parts = rectangle1Input.substring(10, rectangle1Input.length() - 1).split(",");
		int x1Rect1 = Integer.parseInt(rectangle1Parts[0].trim());
		int y1Rect1 = Integer.parseInt(rectangle1Parts[1].trim());
		int x2Rect1 = Integer.parseInt(rectangle1Parts[2].trim());
		int y2Rect1 = Integer.parseInt(rectangle1Parts[3].trim());
	
		System.out.println("rectangle2(x1,y1,x2,y2)");
		String rectangle2Input = scanner.nextLine().trim();
		if (!rectangle2Input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] rectangle2Parts = rectangle2Input.substring(10, rectangle2Input.length() - 1).split(",");
		int x1Rect2 = Integer.parseInt(rectangle2Parts[0].trim());
		int y1Rect2 = Integer.parseInt(rectangle2Parts[1].trim());
		int x2Rect2 = Integer.parseInt(rectangle2Parts[2].trim());
		int y2Rect2 = Integer.parseInt(rectangle2Parts[3].trim());
	
		Rectangle rectangle1 = new Rectangle(x1Rect1, y1Rect1, x2Rect1, y2Rect1);
		Rectangle rectangle2 = new Rectangle(x1Rect2, y1Rect2, x2Rect2, y2Rect2);
		UnionRect unionRect = new UnionRect();
	
		Rectangle unionRectResult = (Rectangle) unionRect.apply(rectangle1, rectangle2);
		System.out.println("Result :\n" + unionRectResult.paint());
	}
	
	private static void diffRect(Scanner scanner) {
		System.out.println("rectangle1(x1,y1,x2,y2)");
		String rectangle1Input = scanner.nextLine().trim();
		if (!rectangle1Input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] rectangle1Parts = rectangle1Input.substring(10, rectangle1Input.length() - 1).split(",");
		int x1Rect1 = Integer.parseInt(rectangle1Parts[0].trim());
		int y1Rect1 = Integer.parseInt(rectangle1Parts[1].trim());
		int x2Rect1 = Integer.parseInt(rectangle1Parts[2].trim());
		int y2Rect1 = Integer.parseInt(rectangle1Parts[3].trim());
	
		System.out.println("rectangle2(x1,y1,x2,y2)");
		String rectangle2Input = scanner.nextLine().trim();
		if (!rectangle2Input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] rectangle2Parts = rectangle2Input.substring(10, rectangle2Input.length() - 1).split(",");
		int x1Rect2 = Integer.parseInt(rectangle2Parts[0].trim());
		int y1Rect2 = Integer.parseInt(rectangle2Parts[1].trim());
		int x2Rect2 = Integer.parseInt(rectangle2Parts[2].trim());
		int y2Rect2 = Integer.parseInt(rectangle2Parts[3].trim());
	
		Rectangle rectangle1 = new Rectangle(x1Rect1, y1Rect1, x2Rect1, y2Rect1);
		Rectangle rectangle2 = new Rectangle(x1Rect2, y1Rect2, x2Rect2, y2Rect2);
		DiffRect diffRect = new DiffRect();
	
		Rectangle diffRectResult = (Rectangle) diffRect.apply(rectangle1, rectangle2);
		System.out.println("Result :\n" + diffRectResult.paint());
	}
	
	private static void interRect(Scanner scanner) {
		System.out.println("rectangle1(x1,y1,x2,y2)");
		String rectangle1Input = scanner.nextLine().trim();
		if (!rectangle1Input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] rectangle1Parts = rectangle1Input.substring(10, rectangle1Input.length() - 1).split(",");
		int x1Rect1 = Integer.parseInt(rectangle1Parts[0].trim());
		int y1Rect1 = Integer.parseInt(rectangle1Parts[1].trim());
		int x2Rect1 = Integer.parseInt(rectangle1Parts[2].trim());
		int y2Rect1 = Integer.parseInt(rectangle1Parts[3].trim());
	
		System.out.println("rectangle2(x1,y1,x2,y2)");
		String rectangle2Input = scanner.nextLine().trim();
		if (!rectangle2Input.matches("^rectangle\\((\\d+),(\\d+),(\\d+),(\\d+)\\)$")) {
			System.out.println("Invalid input format !");
			return;
		}
		String[] rectangle2Parts = rectangle2Input.substring(10, rectangle2Input.length() - 1).split(",");
		int x1Rect2 = Integer.parseInt(rectangle2Parts[0].trim());
		int y1Rect2 = Integer.parseInt(rectangle2Parts[1].trim());
		int x2Rect2 = Integer.parseInt(rectangle2Parts[2].trim());
		int y2Rect2 = Integer.parseInt(rectangle2Parts[3].trim());
	
		Rectangle rectangle1 = new Rectangle(x1Rect1, y1Rect1, x2Rect1, y2Rect1);
		Rectangle rectangle2 = new Rectangle(x1Rect2, y1Rect2, x2Rect2, y2Rect2);
		InterRect interRect = new InterRect();
	
		Rectangle interRectResult = (Rectangle) interRect.apply(rectangle1, rectangle2);
		System.out.println("Result :\n" + interRectResult.paint());
	}
}
