package org.example.povi.domain.diary.enums;

import lombok.Getter;

@Getter
public enum MoodEmoji {
    HAPPY("😊 행복해요", 10),
    JOYFUL("😂 즐거워요", 8),
    CALM("😌 평온해요", 4),
    NEUTRAL("😐 그저 그래요", 0),
    DEPRESSED("😔 우울해요", -4),
    SAD("😢 슬퍼요", -6),
    TIRED("😭 힘들어요", -8),
    ANGRY("😤 화나요", -10);

    private final String label;
    private final int valence;

    MoodEmoji(String label, int valence) {
        this.label = label;
        this.valence = valence;
    }

    public String label() {
        return label;
    }

    public int valence() {
        return valence;
    }

    // 평균 valence와 가장 가까운 감정 반환
    public static MoodEmoji fromValence(double averageScore) {
        double clampedScore = Math.max(-10, Math.min(10, averageScore));
        MoodEmoji mostSimilarEmotion = NEUTRAL;
        double smallestDifference = Double.MAX_VALUE;
        for (MoodEmoji m : values()) {
            double d = Math.abs(m.valence - clampedScore);
            if (d < smallestDifference) {
                smallestDifference = d;
                mostSimilarEmotion = m;
            }
        }
        return mostSimilarEmotion;
    }
}