package bowling;

public class Game {

	private int ball;
	private int firstThrow;
	private int secondThrow;

	private int itsScore = 0;
	private int itsCurrentThrow = 0;
	private int[] itsThrows = new int[21];
	private int itsCurrentFrame = 1;
	private boolean firstThrowInFrame = true;

	public int score() {
		return scoreForFrame(getCurrentFrame() - 1);
	}

	public void add(int pins) {
		this.itsThrows[itsCurrentThrow++] = pins;
		this.itsScore += pins;
		adjustCurrentFrame(pins);
	}

	private void adjustCurrentFrame(int pins) {
		if (firstThrowInFrame == true) {
			if (pins == 10) // strike
				itsCurrentFrame++;
			else
				firstThrowInFrame = false;
		} else {
			firstThrowInFrame = true;
			itsCurrentFrame++;
		}
		itsCurrentFrame = Math.min(11, itsCurrentFrame);
	}

	public int scoreForFrame(int theFrame) {
		ball = 0;
		int score = 0;
		for (int currentFrame = 0; currentFrame < theFrame; currentFrame++) {
			firstThrow = itsThrows[ball];
			if (strike()) {
				ball++;
				score += 10 + nextTwoBalls();
			} else {
				score += handleSecondThrow();
			}
		}

		return score;
	}

	private boolean strike() {
		return itsThrows[ball] == 10;
	}

	private int nextTwoBalls() {
		return itsThrows[ball] + itsThrows[ball + 1];
	}

	private int handleSecondThrow() {
		int score = 0;
		secondThrow = itsThrows[ball + 1];

		int frameScore = firstThrow + secondThrow;
		// spare needs next frames first throw
		if (frameScore == 10) {
			ball += 2;
			score += frameScore + itsThrows[ball];
		} else {
			ball += 2;
			score += frameScore;
		}
		return score;
	}

	public int getCurrentFrame() {
		return itsCurrentFrame;
	}
}
