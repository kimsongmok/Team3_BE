package com.splanet.splanet.teamplan.controller;

import com.splanet.splanet.teamplan.dto.TeamPlanRequestDto;
import com.splanet.splanet.teamplan.dto.TeamPlanResponseDto;
import com.splanet.splanet.teamplan.service.TeamPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams/{teamId}/plans")
public class TeamPlanController implements TeamPlanApi{

  private final TeamPlanService teamPlanService;

  // 팀 플랜 생성
<<<<<<< HEAD
  @PostMapping
=======
  @Override
>>>>>>> weekly/11
  public ResponseEntity<TeamPlanResponseDto> createTeamPlan(
          @AuthenticationPrincipal Long userId,
          @PathVariable Long teamId,
          @RequestBody TeamPlanRequestDto requestDto) {
    TeamPlanResponseDto responseDto = teamPlanService.createTeamPlan(userId, teamId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  // 팀 플랜 조회
<<<<<<< HEAD
  @GetMapping("/{planId}")
=======
  @Override
>>>>>>> weekly/11
  public ResponseEntity<TeamPlanResponseDto> getTeamPlan(
          @PathVariable Long teamId,
          @PathVariable Long planId) {
    TeamPlanResponseDto responseDto = teamPlanService.getTeamPlan(teamId, planId);
    return ResponseEntity.ok(responseDto);
  }

  // 팀의 모든 플랜 조회
<<<<<<< HEAD
  @GetMapping
=======
  @Override
>>>>>>> weekly/11
  public ResponseEntity<List<TeamPlanResponseDto>> getAllTeamPlans(@PathVariable Long teamId) {
    List<TeamPlanResponseDto> plans = teamPlanService.getAllTeamPlans(teamId);
    return ResponseEntity.ok(plans);
  }

  // 팀 플랜 수정
<<<<<<< HEAD
  @PutMapping("/{planId}")
=======
  @Override
>>>>>>> weekly/11
  public ResponseEntity<TeamPlanResponseDto> updateTeamPlan(
          @AuthenticationPrincipal Long userId,
          @PathVariable Long teamId,
          @PathVariable Long planId,
          @RequestBody TeamPlanRequestDto requestDto) {
    TeamPlanResponseDto updatedPlan = teamPlanService.updateTeamPlan(userId, teamId, planId, requestDto);
    return ResponseEntity.ok(updatedPlan);
  }

  // 팀 플랜 삭제
<<<<<<< HEAD
  @DeleteMapping("/{planId}")
=======
  @Override
>>>>>>> weekly/11
  public ResponseEntity<Void> deleteTeamPlan(
          @AuthenticationPrincipal Long userId,
          @PathVariable Long teamId,
          @PathVariable Long planId) {
    teamPlanService.deleteTeamPlan(userId, teamId, planId);
    return ResponseEntity.noContent().build();
  }
}