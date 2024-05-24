package com.ighorosipov.testandroid.data.mapper

import com.ighorosipov.testandroid.data.remote.dto.CoinDetailDto
import com.ighorosipov.testandroid.data.remote.dto.CoinDto
import com.ighorosipov.testandroid.data.remote.dto.TeamMember
import com.ighorosipov.testandroid.domain.model.Coin
import com.ighorosipov.testandroid.domain.model.CoinDetail

class CoinMapper {

    fun coinDtoToCoin(coinDto: CoinDto) : Coin {
        return Coin(
            id = coinDto.id,
            isActive = coinDto.isActive,
            name = coinDto.name,
            rank = coinDto.rank,
            symbol = coinDto.symbol
        )
    }

    fun coinDetailDtoToCoinDetail(coinDetailDto: CoinDetailDto): CoinDetail {
        return CoinDetail(
            coinId = coinDetailDto.id,
            name = coinDetailDto.name,
            description = coinDetailDto.description,
            symbol = coinDetailDto.symbol,
            rank = coinDetailDto.rank,
            isActive = coinDetailDto.isActive,
            tags = coinDetailDto.tags.map { it.name },
            team = coinDetailDto.team.map { teamMemberDataToTeamMemberDomain(it) }
        )
    }

    private fun teamMemberDataToTeamMemberDomain(teamMember: TeamMember): com.ighorosipov.testandroid.domain.model.TeamMember {
        return com.ighorosipov.testandroid.domain.model.TeamMember(
            id = teamMember.id,
            name = teamMember.name,
            position = teamMember.position
        )
    }

}