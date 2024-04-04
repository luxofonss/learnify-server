package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.domain.entities.Subject;
import com.quyennv.lms.core.domain.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateAssignmentDetailUseCase extends UpdateAssignmentUseCase{
    public UpdateAssignmentDetailUseCase(AssignmentRepository assignmentRepository) {
        super(assignmentRepository);
    }

    @Override
    public Assignment update(InputValues input, Assignment assignment) {
        Assignment updateAssignment = Assignment
                .builder()
                .id(input.getId())
                .title(input.getTitle())
                .description(input.getDescription())
                .teacher(Objects.nonNull(input.getTeacherId()) ? User.builder().id(input.getTeacherId()).build() : null)
                .totalMark(assignment.getTotalMark())
                .subject(Objects.nonNull(input.getSubjectId()) ? Subject.builder().id(input.getSubjectId()).build() : null)
                .build();

        if (Objects.nonNull(input.getQuestions()) && !input.getQuestions().isEmpty()) {
            // reset total mark for future functions
            updateAssignment.setTotalMark(0);
            input.getQuestions().forEach(
                    q-> {
                        List<Question> questions = mapQuestions(input);
                        updateAssignment.setQuestions(questions);
                    }
            );
        } else {
            throw new RuntimeException("Assignment must have question");
        }

        AtomicReference<Integer> totalMark = new AtomicReference<>(0);

        updateAssignment.getQuestions().forEach(
                q -> totalMark.updateAndGet(v -> v + updateAssignment.getTotalMark())
        );

        updateAssignment.setTotalMark(totalMark.get());

        return assignment.update(updateAssignment);
    }
}
